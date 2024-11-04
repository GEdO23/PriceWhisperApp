package br.com.pricewhisper

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.screens.HomeScreen
import br.com.pricewhisper.ui.screens.products.ProductDetailsScreen
import br.com.pricewhisper.ui.screens.products.ProductFormScreen
import br.com.pricewhisper.ui.screens.products.ProductListScreen
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import br.com.pricewhisper.ui.viewmodels.ProductViewModel

enum class PriceWhisperScreen(val title: String) {
    HomeScreen(title = "Home"),
    ProductListScreen(title = "Product List"),
    RegisterProductScreen(title = "New Product"),
    EditProductScreen(title = "Edit Product"),
    ProductDetailsScreen(title = "Product Details")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceWhisperAppBar(
    modifier: Modifier = Modifier,
    currentScreen: PriceWhisperScreen,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = currentScreen.title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun PriceWhisperApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = PriceWhisperScreen.entries.find {
        backStackEntry.value?.destination?.route?.startsWith(it.name) == true
    } ?: PriceWhisperScreen.HomeScreen

    Scaffold(
        topBar = {
            PriceWhisperAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = PriceWhisperScreen.HomeScreen.name,
                modifier = Modifier.padding(16.dp)
            ) {
                composable(PriceWhisperScreen.HomeScreen.name) {
                    HomeScreen(
                        onClickProductsActionButton = {
                            navController.navigate(
                                PriceWhisperScreen.ProductListScreen.name
                            )
                        },
                        onClickProfileActionButton = {},
                        onClickSettingsActionButton = {}
                    )
                }

                navigation(
                    route = "products",
                    startDestination = PriceWhisperScreen.ProductListScreen.name
                ) {
                    composable(PriceWhisperScreen.ProductListScreen.name) {
                        val viewModel = ProductViewModel()
                        viewModel.getAll()

                        ProductListScreen(
                            productList = viewModel.productList,
                            onClickNewProductFAB = {
                                navController.navigate(PriceWhisperScreen.RegisterProductScreen.name)
                            },
                            onClickProductItem = { product ->
                                Log.d("PRICE_WHISPER", "Product Clicked: $product")
                                navController.navigate("${PriceWhisperScreen.ProductDetailsScreen.name}/${product.id}")
                            }
                        )
                    }
                    composable(PriceWhisperScreen.RegisterProductScreen.name) {
                        val viewModel = ProductViewModel()
                        ProductFormScreen(
                            onClickBtnSubmit = { product ->
                                viewModel.save(product)
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("${PriceWhisperScreen.ProductDetailsScreen.name}/{productId}") {
                        val viewModel = ProductViewModel()
                        val productId = it.arguments?.getString("productId")

                        productId?.let {
                            val productState = remember { mutableStateOf<Product?>(null) }

                            LaunchedEffect(productId) {
                                viewModel.getById(
                                    id = productId,
                                    onResult = { foundProduct ->
                                        productState.value = foundProduct
                                    }
                                )
                            }

                            productState.value?.let { product ->
                                ProductDetailsScreen(
                                    product = product,
                                    onClickEditMode = {
                                        navController.navigate("${PriceWhisperScreen.EditProductScreen.name}/$productId")
                                    }
                                )
                            } ?: run {
                                Text("Carregando...")
                            }
                        }
                    }
                    composable("${PriceWhisperScreen.EditProductScreen.name}/{productId}") {
                        val viewModel = ProductViewModel()
                        val productId = it.arguments?.getString("productId")

                        productId?.let { id ->
                            val productState = remember { mutableStateOf<Product?>(null) }

                            LaunchedEffect(productId) {
                                viewModel.getById(
                                    id = id,
                                    onResult = { foundProduct ->
                                        productState.value = foundProduct
                                    }
                                )
                            }

                            productState.value?.let { product ->
                                ProductFormScreen(
                                    filledProduct = product,
                                    onClickBtnSubmit = { newProduct ->
                                        viewModel.edit(
                                            id = id,
                                            newProduct = newProduct
                                        )
                                        navController.popBackStack()
                                    }
                                )
                            } ?: run {
                                Text("Carregando...")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PriceWhisperPreview() {
    PriceWhisperTheme {
        PriceWhisperApp()
    }
}