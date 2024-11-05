package br.com.pricewhisper.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.R
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickProductsActionButton: () -> Unit,
    onClickProfileActionButton: () -> Unit,
    onClickSettingsActionButton: () -> Unit
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
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp),
            label = R.string.actionbtn_products_label,
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = stringResource(R.string.actionbtn_products_description),
            onClick = { onClickProductsActionButton() }
        )
        ActionButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp),
            label = R.string.actionbtn_profile_label,
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.actionbtn_profile_description),
            onClick = { onClickProfileActionButton() }
        )
        ActionButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 100.dp),
            label = R.string.actionbtn_settings_label,
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(R.string.actionbtn_settings_description),
            onClick = { onClickSettingsActionButton() }
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FilledIconButton(onClick = onClick) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = stringResource(label),
            textAlign = TextAlign.Center
        )
    }
}

@PreviewLightDark
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