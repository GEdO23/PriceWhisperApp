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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import java.math.BigDecimal

/**
 * Composable function to display a list of products.
 *
 * @param modifier Modifier to be applied to the list.
 * @param productList List of products to be displayed.
 * @param onClickItem Callback function to be invoked when a product is clicked.
 * @param onClickDelete Callback function to be invoked when the delete icon is clicked.
 */
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

/**
 * Composable function to display a single product item in the list.
 *
 * @param modifier Modifier to be applied to the item.
 * @param product The product data to be displayed.
 * @param onClick Callback function to be invoked when the item is clicked.
 * @param onClickDelete Callback function to be invoked when the delete icon is clicked.
 */
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
                        contentDescription = stringResource(R.string.productlist_item_icon_description_delete)
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