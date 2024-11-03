package br.com.pricewhisper.ui.screens.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.components.products.ProductCard
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import java.math.BigDecimal

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    product: Product,
    onClickEditMode: (product: Product) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 8.dp,
            )
        ) {
            ProductCard(
                modifier = Modifier.fillMaxWidth(),
                product = product
            )
        }
        FloatingActionButton(
            onClick = { onClickEditMode(product) },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Product",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ProductDetailsPreview() {
    PriceWhisperTheme {
        Surface {
            ProductDetailsScreen(
                product = Product(
                    name = "Arroz",
                    price = BigDecimal("25.00"),
                    stock = 25u,
                    description = LoremIpsum(50).values.first()
                ),
                onClickEditMode = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}