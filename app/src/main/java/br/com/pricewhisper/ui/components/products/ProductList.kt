package br.com.pricewhisper.ui.components.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.pricewhisper.models.Product

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    productList: List<Product>,
    onClickItem: (productClicked: Product) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = productList) { product ->
            ProductListItem(
                product = product,
                onClick = onClickItem
            )
        }
    }
}

@Composable
private fun ProductListItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: (productClicked: Product) -> Unit
) {
    Box(modifier = modifier.clickable { onClick(product) }) {
        ListItem(
            headlineContent = { Text(product.name) },
            supportingContent = { Text("R$ ${product.price}") }
        )
    }
}