package br.com.pricewhisper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.pricewhisper.ui.screens.PriceWhisperApp
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceWhisperTheme {
                PriceWhisperApp()
            }
        }
    }
}