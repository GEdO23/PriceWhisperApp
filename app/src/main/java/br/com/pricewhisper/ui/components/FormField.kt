package br.com.pricewhisper.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun FormFieldText(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(label) }
    )
}

@Composable
fun FormFieldPrice(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String,
    currencyCode: String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        prefix = { Text(currencyCode) },
        label = { Text(label) }
    )
}

@Composable
fun FormFieldStock(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(label) }
    )
}