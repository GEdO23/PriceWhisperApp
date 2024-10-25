package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun ProductListScreen(
    productList: List<Product>,
    onClickNewProductFAB: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ProductList(productList = productList)
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

@Composable
private fun ProductList(
    productList: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = productList) {
            ListItem(
                headlineContent = { Text(it.name) },
                supportingContent = { Text("R$ ${it.price}") }
            )
        }
    }
}

@Preview
@Composable
private fun ProductListPreview() {
    PriceWhisperTheme {
        Surface {
            ProductListScreen(
                productList = listOf(),
                onClickNewProductFAB = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            )
        }
    }
}