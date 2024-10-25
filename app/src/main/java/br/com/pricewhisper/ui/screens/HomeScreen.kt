package br.com.pricewhisper.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun HomeScreen(
    onClickProductsActionButton: () -> Unit,
    onClickProfileActionButton: () -> Unit,
    onClickSettingsActionButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
    ) {
        ButtonGroup(
            onClickProductsActionButton = onClickProductsActionButton,
            onClickProfileActionButton = onClickProfileActionButton,
            onClickSettingsActionButton = onClickSettingsActionButton,
            modifier = Modifier
                .border(2.dp, Color.Gray, RoundedCornerShape(24.dp))
                .padding(32.dp),
        )
    }
}

@Composable
private fun ButtonGroup(
    modifier: Modifier = Modifier,
    onClickProductsActionButton: () -> Unit,
    onClickProfileActionButton: () -> Unit,
    onClickSettingsActionButton: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        ActionButton(
            label = "Products",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) { onClickProductsActionButton() }
        ActionButton(
            label = "Profile",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) { onClickProfileActionButton() }
        ActionButton(
            label = "Settings",
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp)
        ) { onClickSettingsActionButton() }
    }
}


@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FilledIconButton(onClick = onClick) { }
        Text(
            text = label,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomePreview() {
    PriceWhisperTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HomeScreen(
                onClickProductsActionButton = {},
                onClickProfileActionButton = {},
                onClickSettingsActionButton = {}
            )
        }
    }
}