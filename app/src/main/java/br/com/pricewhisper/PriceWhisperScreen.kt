package br.com.pricewhisper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.pricewhisper.ui.ProductFormScreen
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun PriceWhisperApp() {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ProductFormScreen()
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