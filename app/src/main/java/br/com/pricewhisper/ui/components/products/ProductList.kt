package br.com.pricewhisper.ui.components.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.pricewhisper.models.Product
import java.math.BigDecimal

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    productList: List<Product>,
    onClickItem: (productClicked: Product) -> Unit,
    onClickDelete: (id: String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = productList) { product ->
            ProductListItem(
                product = product,
                onClick = onClickItem,
                onClickDelete = onClickDelete
            )
        }
    }
}

@Composable
private fun ProductListItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (productClicked: Product) -> Unit,
    onClickDelete: (id: String) -> Unit
) {
    Box(modifier = modifier.clickable { onClick(product) }) {
        ListItem(
            headlineContent = {
                Text(product.name)
            },
            supportingContent = {
                Text("R$ ${product.price}")
            },
            trailingContent = {
                IconButton(
                    onClick = { onClickDelete(product.id) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "Delete Product"
                    )

                }
            }
        )
    }
}

@Preview
@Composable
private fun ProductListItemPreview() {
    val productExample = Product(
        name = "Produto Exemplo",
        price = BigDecimal("23.00"),
        stock = 10u,
        description = "Produto exemplo a fim de testes"
    )

    ProductListItem(
        product = productExample,
        onClick = {},
        onClickDelete = {}
    )
}