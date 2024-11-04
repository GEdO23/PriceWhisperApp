package br.com.pricewhisper.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.MainActivity.PriceWhisperScreen
import br.com.pricewhisper.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceWhisperAppBar(
    modifier: Modifier = Modifier,
    currentScreen: PriceWhisperScreen,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
    onSwitchTheme: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.top_app_bar_navigation_icon_description),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onSwitchTheme) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(R.string.switch_theme_description),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun PriceWhisperAppBarPreview() {
    val currentScreen = PriceWhisperScreen.valueOf(PriceWhisperScreen.HomeScreen.name)

    PriceWhisperAppBar(
        currentScreen = currentScreen,
        canNavigateBack = false,
        onSwitchTheme = {}
    )
}