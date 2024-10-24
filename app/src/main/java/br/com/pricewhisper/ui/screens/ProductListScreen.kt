package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.pricewhisper.productFormDestination
import br.com.pricewhisper.ui.viewmodels.ProductViewModel

@Composable
fun ProductListScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = ProductViewModel()

    Box {
        Column(modifier = modifier) {
            Text(
                text = "Products List",
                fontSize = 24.sp,
                fontWeight = FontWeight(700)
            )

            ProductList(viewModel = viewModel)
        }

        FloatingActionButton(
            onClick = { navController.navigate(productFormDestination.route) },
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
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.getAll()

    LazyColumn(modifier = modifier) {
        items(items = viewModel.productList) {
            ListItem(
                headlineContent = { Text(it.name) },
                supportingContent = { Text("R$ ${it.price}") }
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