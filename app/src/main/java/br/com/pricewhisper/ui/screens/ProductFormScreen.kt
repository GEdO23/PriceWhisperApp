package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.components.products.ProductForm
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun ProductFormScreen(
    modifier: Modifier = Modifier,
    onClickBtnSubmit: (product: Product) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val stock = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    ProductForm(
        name = name,
        price = price,
        stock = stock,
        description = description,
        onSubmit = { onClickBtnSubmit(it) },
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ProductFormPreview() {
    PriceWhisperTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProductFormScreen(
                onClickBtnSubmit = {}
            )
        }
    }
}
