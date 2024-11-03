package br.com.pricewhisper.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class TextAndLabelStyle(
    val primaryTextColor: Color,
    val secondaryTextColor: Color
)

@Composable
fun TextAndLabel(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    style: TextAndLabelStyle,
    label: String,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        Text(
            text = label,
            color = style.primaryTextColor,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = text,
            color = style.secondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun TextAndLabel(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical,
    style: TextAndLabelStyle,
    label: String,
    text: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = label,
            color = style.primaryTextColor,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = text,
            color = style.secondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}