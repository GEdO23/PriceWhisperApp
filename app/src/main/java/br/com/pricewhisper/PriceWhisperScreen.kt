package br.com.pricewhisper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.pricewhisper.ui.screens.HomeScreen
import br.com.pricewhisper.ui.screens.ProductFormScreen
import br.com.pricewhisper.ui.screens.ProductListScreen
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun PriceWhisperApp() {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = homeDestination.route
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

@Preview
@Composable
private fun PriceWhisperAppPreview() {
    PriceWhisperTheme {
        PriceWhisperApp()
    }
}