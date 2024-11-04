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

/**
 * Composable function to display a form for creating or editing a product.
 *
 * @param modifier Modifier to be applied to the form.
 * @param name MutableState holding the product name.
 * @param price MutableState holding the product price.
 * @param stock MutableState holding the product stock.
 * @param description MutableState holding the product description.
 * @param onSubmit Callback function to be invoked when the form is submitted.
 */
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

/**
 * Composable function to display a submit button for the product form.
 *
 * @param modifier Modifier to be applied to the button.
 * @param text Resource ID for the button text.
 * @param product The product data to be submitted.
 * @param onSubmit Callback function to be invoked when the button is clicked.
 */
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

/**
 * Converts form input values to a Product object.
 *
 * @param name The product name.
 * @param price The product price as a string.
 * @param stock The product stock as a string.
 * @param description The product description.
 * @return A Product object with the provided values.
 */
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