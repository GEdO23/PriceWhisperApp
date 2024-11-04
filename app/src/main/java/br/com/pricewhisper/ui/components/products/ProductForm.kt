package br.com.pricewhisper.ui.components.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.components.FormFieldPrice
import br.com.pricewhisper.ui.components.FormFieldStock
import br.com.pricewhisper.ui.components.FormFieldText
import java.math.BigDecimal

@Composable
fun ProductForm(
    modifier: Modifier = Modifier,
    name: MutableState<String>,
    price: MutableState<String>,
    stock: MutableState<String>,
    description: MutableState<String>,
    onSubmit: (product: Product) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            FormFieldText(
                value = name,
                label = "Name"
            )
            FormFieldPrice(
                value = price,
                label = "Price",
                currencyCode = "R$"
            )
            FormFieldStock(
                value = stock,
                label = "Stock"
            )
            FormFieldText(
                value = description,
                label = "Description"
            )
        }
        ProductFormSubmitButton(
            product = toProduct(
                name = name.value,
                price = price.value,
                stock = stock.value,
                description = description.value
            ),
            onSubmit = onSubmit
        )
    }
}

@Composable
private fun ProductFormSubmitButton(
    modifier: Modifier = Modifier,
    product: Product,
    onSubmit: (product: Product) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onSubmit(product) }
    ) { Text("Submit") }
}

private fun toProduct(
    name: String,
    price: String,
    stock: String,
    description: String,
): Product = Product(
    name = name,
    price = price.toBigDecimalOrNull() ?: BigDecimal("0.0"),
    stock = stock.toIntOrNull()?.toUInt() ?: 1u,
    description = description
)