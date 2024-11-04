package br.com.pricewhisper

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
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
import br.com.pricewhisper.ui.viewmodels.ThemeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val themeViewModel = viewModels<ThemeViewModel>()
    private val productViewModel = viewModels<ProductViewModel>()

    enum class PriceWhisperScreen(@StringRes val title: Int) {
        HomeScreen(title = R.string.home_screen_title),
        ProductListScreen(title = R.string.product_list_screen_title),
        RegisterProductScreen(title = R.string.register_product_screen_title),
        EditProductScreen(title = R.string.edit_product_screen_title),
        ProductDetailsScreen(title = R.string.product_details_screen_title)
    }

    enum class PriceWhisperNavGraph {
        Products,
        Profile,
        Settings
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        lifecycleScope.launch {
            themeViewModel.value.currentPrefs.value = getPreferences(Context.MODE_PRIVATE)
            themeViewModel.value.isDarkModeOn.value = themeViewModel.value.getCurrentPalette()
        }

        enableEdgeToEdge()
        setContent {
            PriceWhisperTheme(
                darkMode = remember { themeViewModel.value.isDarkModeOn }
            ) {
                PriceWhisperApp(
                    viewModel = productViewModel
                )
            }
        }
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
            title = { Text(text = getString(currentScreen.title)) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.top_app_bar_navigation_icon_description),
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
        viewModel: Lazy<ProductViewModel>,
        navController: NavHostController = rememberNavController()
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = PriceWhisperScreen.valueOf(
            backStackEntry.value?.destination?.route ?: PriceWhisperScreen.HomeScreen.name
        )

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
                                themeViewModel.value.switchPalette()
                            },
                            onClickProfileActionButton = {},
                            onClickSettingsActionButton = {}
                        )
                    }

                    navigation(
                        route = PriceWhisperNavGraph.Products.name,
                        startDestination = PriceWhisperScreen.ProductListScreen.name
                    ) {
                        composable(PriceWhisperScreen.ProductListScreen.name) {
                            LaunchedEffect(true) {
                                viewModel.value.loadProducts()
                                viewModel.value.currentProduct.value = Product()
                            }

                            ProductListScreen(
                                productList = viewModel.value.productList,
                                onClickNewProductFAB = {
                                    navController.navigate(PriceWhisperScreen.RegisterProductScreen.name)
                                },
                                onClickProductItem = { product ->
                                    viewModel.value.currentProduct.value = product
                                    navController.navigate(PriceWhisperScreen.ProductDetailsScreen.name)
                                },
                                onClickDelete = { productId ->
                                    viewModel.value.deleteProduct(
                                        id = productId,
                                        onResult = {
                                            viewModel.value.loadProducts()
                                        }
                                    )
                                }
                            )
                        }
                        composable(PriceWhisperScreen.RegisterProductScreen.name) {
                            ProductFormScreen(
                                onClickBtnSubmit = { product ->
                                    viewModel.value.saveProduct(product)
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(PriceWhisperScreen.ProductDetailsScreen.name) {
                            val productClicked = viewModel.value.currentProduct.value

                            productClicked?.let { product ->
                                ProductDetailsScreen(
                                    product = product,
                                    onClickEditMode = {
                                        navController.navigate(PriceWhisperScreen.EditProductScreen.name)
                                    }
                                )
                            } ?: Text(stringResource(R.string.loading_text))
                        }
                        composable(PriceWhisperScreen.EditProductScreen.name) {
                            val productClicked = viewModel.value.currentProduct.value

                            productClicked?.let { product ->
                                ProductFormScreen(
                                    filledProduct = product,
                                    onClickBtnSubmit = { newProduct ->
                                        viewModel.value.editProduct(
                                            id = product.id,
                                            newProduct = newProduct
                                        )
                                        navController.popBackStack()
                                    }
                                )
                            } ?: Text(stringResource(R.string.loading_text))
                        }
                    }
                }
            }
        }
    }

}