package br.com.pricewhisper.ui.components.products

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.R
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
                label = R.string.form_field_label_product_name
            )
            FormFieldPrice(
                value = price,
                label = R.string.form_field_label_product_price,
                currencyCode = "R$"
            )
            FormFieldStock(
                value = stock,
                label = R.string.form_field_label_product_stock
            )
            FormFieldText(
                value = description,
                label = R.string.form_field_label_product_description
            )
        }
        ProductFormSubmitButton(
            text = R.string.btn_submit_text,
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
    @StringRes text: Int,
    product: Product,
    onSubmit: (product: Product) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onSubmit(product) }
    ) { Text(stringResource(text)) }
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