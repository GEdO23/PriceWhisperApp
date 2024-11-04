package br.com.pricewhisper.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType

/**
 * Composable function to display a text form field.
 *
 * @param modifier Modifier to be applied to the text field.
 * @param value MutableState holding the text value.
 * @param label Resource ID for the text field label.
 */
@Composable
fun FormFieldText(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    @StringRes label: Int
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(stringResource(label)) }
    )
}

/**
 * Composable function to display a price form field with a currency prefix.
 *
 * @param modifier Modifier to be applied to the price field.
 * @param value MutableState holding the price value.
 * @param label Resource ID for the price field label.
 * @param currencyCode Currency code to be displayed as a prefix.
 */
@Composable
fun FormFieldPrice(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    @StringRes label: Int,
    currencyCode: String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        prefix = { Text(currencyCode) },
        label = { Text(stringResource(label)) }
    )
}

/**
 * Composable function to display a stock form field.
 *
 * @param modifier Modifier to be applied to the stock field.
 * @param value MutableState holding the stock value.
 * @param label Resource ID for the stock field label.
 */
@Composable
fun FormFieldStock(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    @StringRes label: Int
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(stringResource(label)) }
    )
}