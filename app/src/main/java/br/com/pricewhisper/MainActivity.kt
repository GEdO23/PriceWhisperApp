package br.com.pricewhisper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import br.com.pricewhisper.ui.viewmodels.ProductViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = viewModels<ProductViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceWhisperTheme {
                PriceWhisperApp(
                    viewModel = viewModel
                )
            }
        }
    }
}