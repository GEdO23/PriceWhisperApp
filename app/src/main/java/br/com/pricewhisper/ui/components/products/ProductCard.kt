package br.com.pricewhisper.ui.components.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.components.TextAndLabel
import br.com.pricewhisper.ui.components.TextAndLabelStyle
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import java.math.BigDecimal

data class ProductCardStyle(
    val cardColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val cardBorderColor: Color
)

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    style: ProductCardStyle = ProductCardStyle(
        cardColor = MaterialTheme.colorScheme.surface,
        primaryTextColor = MaterialTheme.colorScheme.onSurface,
        secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        cardBorderColor = MaterialTheme.colorScheme.outline
    ),
    product: Product
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
    ) {
        Column(
            modifier = Modifier
                .background(style.cardColor)
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            ProductCardHeader(
                product = product,
                style = style,
                modifier = Modifier.fillMaxWidth()
            )
            ProductCardBody(
                product = product,
                style = style,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ProductCardHeader(
    modifier: Modifier = Modifier,
    style: ProductCardStyle,
    product: Product
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = product.name,
            color = style.primaryTextColor,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun ProductCardBody(
    modifier: Modifier = Modifier,
    style: ProductCardStyle,
    product: Product
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        TextAndLabel(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
            style = TextAndLabelStyle(
                primaryTextColor = style.primaryTextColor,
                secondaryTextColor = style.secondaryTextColor
            ),
            label = R.string.product_details_label_price,
            text = "R$ ${product.price}"
        )
        TextAndLabel(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            style = TextAndLabelStyle(
                primaryTextColor = style.primaryTextColor,
                secondaryTextColor = style.secondaryTextColor
            ),
            label = R.string.product_details_label_description,
            text = product.description
        )
        TextAndLabel(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
            style = TextAndLabelStyle(
                primaryTextColor = style.primaryTextColor,
                secondaryTextColor = style.secondaryTextColor
            ),
            label = R.string.product_details_label_stock,
            text = "${product.stock} em estoque"
        )
    }
}

@PreviewLightDark
@Composable
private fun ProductCardPreview() {
    val productExample = Product(
        name = "Arroz",
        price = BigDecimal("25.00"),
        stock = 25u,
        description = LoremIpsum(50).values.first()
    )
    PriceWhisperTheme {
        Surface {
            ProductCard(
                modifier = Modifier.padding(16.dp),
                product = productExample
            )
        }
    }
}