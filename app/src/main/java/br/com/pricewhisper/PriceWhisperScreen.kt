package br.com.pricewhisper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.ui.HomeScreen
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun PriceWhisperApp() {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeScreen(modifier = Modifier.padding(16.dp))
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