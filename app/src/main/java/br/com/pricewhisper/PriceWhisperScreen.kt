package br.com.pricewhisper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.pricewhisper.ui.screens.HomeScreen
import br.com.pricewhisper.ui.screens.ProductFormScreen
import br.com.pricewhisper.ui.screens.ProductListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceWhisperAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@Composable
fun PriceWhisperApp() {
    Scaffold(topBar = { PriceWhisperAppBar() }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = homeDestination.route,
                modifier = Modifier.padding(16.dp)
            ) {
                composable(homeDestination.route) {
                    HomeScreen(navController = navController)
                }
                composable(productListDestination.route) {
                    ProductListScreen(navController = navController)
                }
                composable(productFormDestination.route) {
                    ProductFormScreen(navController = navController)
                }
            }
        }
    }
}