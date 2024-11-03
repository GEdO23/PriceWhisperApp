package br.com.pricewhisper.ui.screens.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.components.products.ProductList
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import java.math.BigDecimal

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    productList: List<Product>,
    onClickNewProductFAB: () -> Unit,
    onClickProductItem: (productClicked: Product) -> Unit
) {
    Box(modifier = modifier) {
        ProductList(
            productList = productList,
            onClickItem = { onClickProductItem(it) }
        )
        FloatingActionButton(
            onClick = onClickNewProductFAB,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add new product"
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ProductListPreview() {
    val productList = mutableListOf<Product>()
    repeat(50) { position ->
        productList.add(
            Product(
                name = "Product #${position + 1}",
                price = BigDecimal("23.00")
            )
        )
    }

    PriceWhisperTheme {
        Surface {
            ProductListScreen(
                productList = productList,
                onClickNewProductFAB = {},
                onClickProductItem = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            )
        }
    }
}