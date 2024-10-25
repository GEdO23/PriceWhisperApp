package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import java.math.BigDecimal

@Composable
fun ProductScreen(
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

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    cardColor: Color = MaterialTheme.colorScheme.surface,
    primaryTextColor: Color = MaterialTheme.colorScheme.onSurface,
    secondaryTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    cardBorderColor: Color = MaterialTheme.colorScheme.outline
) {
    Card(
        modifier = modifier
            .border(
                width = 1.dp,
                color = cardBorderColor,
                shape = RoundedCornerShape(size = 16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .background(cardColor)
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            ProductCardHeader(
                product = product,
                primaryTextColor = primaryTextColor,
                secondaryTextColor = secondaryTextColor,
                modifier = Modifier.fillMaxWidth()
            )
            ProductCardBody(
                product = product,
                primaryTextColor = primaryTextColor,
                secondaryTextColor = secondaryTextColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ProductCardHeader(
    modifier: Modifier = Modifier,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    product: Product
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Bottom
    ) {
        // TODO: Preencher somente o espaço disponível, não ocupar o espaço do preço
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Bottom)
        ) {
            ProductCardCategory(
                primaryTextColor = primaryTextColor,
                secondaryTextColor = secondaryTextColor,
                category = "Comida"
            )
            Text(
                text = product.name,
                color = primaryTextColor,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Text(
            text = "R$ ${product.price}",
            color = primaryTextColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun ProductCardCategory(
    modifier: Modifier = Modifier,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    category: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start)
    ) {
        Text(
            text = "Categoria:",
            color = primaryTextColor,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = category,
            color = secondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun ProductCardBody(
    modifier: Modifier = Modifier,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    product: Product
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        ProductCardDescription(
            modifier = Modifier.fillMaxWidth(),
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor,
            description = product.description
        )
        ProductCardStock(
            modifier = Modifier.fillMaxWidth(),
            primaryTextColor = primaryTextColor,
            secondaryTextColor = secondaryTextColor,
            stock = product.stock
        )
    }
}

@Composable
private fun ProductCardDescription(
    modifier: Modifier = Modifier,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    description: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top)
    ) {
        Text(
            text = "Descrição:",
            color = primaryTextColor,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = description,
            color = secondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun ProductCardStock(
    modifier: Modifier = Modifier,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    stock: UInt
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start)
    ) {
        Text(
            text = "Estoque:",
            color = primaryTextColor,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "$stock em estoque",
            color = secondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@PreviewLightDark
@Composable
private fun ProductPreview() {
    PriceWhisperTheme {
        Surface {
            ProductScreen(
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