package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pricewhisper.ui.theme.PriceWhisperTheme
import br.com.pricewhisper.ui.viewmodels.ProductViewModel

@Composable
fun ProductListScreen(modifier: Modifier = Modifier) {
    val viewModel = ProductViewModel()

    Column(modifier = modifier) {
        Text(
            text = "Products List",
            fontSize = 24.sp,
            fontWeight = FontWeight(700)
        )

        viewModel.getAll()

        ProductList(viewModel = viewModel)
    }
}

@Composable
private fun ProductList(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = viewModel.productList) {
            ListItem(
                headlineContent = { Text(it.name) },
                supportingContent = { Text("R$${it.price}")}
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }

}