package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.productListDestination
import br.com.pricewhisper.ui.viewmodels.ProductViewModel
import java.math.BigDecimal

@Composable
fun ProductFormScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = ProductViewModel()

    val name = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val stock = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Create Product",
            fontSize = 24.sp
        )
        Column {
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") }
            )
            OutlinedTextField(
                value = price.value,
                onValueChange = { price.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                prefix = { Text("R$") },
                label = { Text("Price") }
            )
            OutlinedTextField(
                value = stock.value,
                onValueChange = { stock.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Stock") }
            )
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") }
            )
        }
        Button(onClick = {
            val product = Product(
                name = name.value,
                price = price.value.toBigDecimalOrNull() ?: BigDecimal("0.0"),
                stock = stock.value.toIntOrNull()?.toUInt() ?: 1u,
                description = description.value
            )
            viewModel.save(product = product)
            navController.navigate(productListDestination.route)
        }) { Text("Submit") }
    }
}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun ProductFormPreview() {
//    PriceWhisperTheme {
//        ProductFormScreen(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            navController = navController
//        )
//    }
//}
