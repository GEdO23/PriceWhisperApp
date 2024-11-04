package br.com.pricewhisper.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

/**
 * Data class representing the style attributes for the TextAndLabel composable.
 *
 * @property primaryTextColor The color to be used for the primary text.
 * @property secondaryTextColor The color to be used for the secondary text.
 */
data class TextAndLabelStyle(
    val primaryTextColor: Color,
    val secondaryTextColor: Color
)

/**
 * Composable function to display a label and text in a horizontal arrangement.
 *
 * @param modifier Modifier to be applied to the Row.
 * @param horizontalArrangement Arrangement for the horizontal alignment of the Row's children.
 * @param style Style attributes for the text and label.
 * @param label Resource ID for the label text.
 * @param text The text to be displayed next to the label.
 */
@Composable
fun TextAndLabel(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    style: TextAndLabelStyle,
    @StringRes label: Int,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        Text(
            text = stringResource(label),
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

/**
 * Composable function to display a label and text in a vertical arrangement.
 *
 * @param modifier Modifier to be applied to the Column.
 * @param verticalArrangement Arrangement for the vertical alignment of the Column's children.
 * @param style Style attributes for the text and label.
 * @param label Resource ID for the label text.
 * @param text The text to be displayed below the label.
 */
@Composable
fun TextAndLabel(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical,
    style: TextAndLabelStyle,
    @StringRes label: Int,
    text: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = stringResource(label),
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