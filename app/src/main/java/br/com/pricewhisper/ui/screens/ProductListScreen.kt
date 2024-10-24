package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.pricewhisper.ui.viewmodels.ProductViewModel

@Composable
fun ProductListScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
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
                supportingContent = { Text("R$${it.price}") }
            )
        }
    }
}

//@Preview
//@Composable
//private fun ProductListPreview() {
//    PriceWhisperTheme {
//        Surface {
//            ProductListScreen(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                navController = navController
//            )
//        }
//    }
//}