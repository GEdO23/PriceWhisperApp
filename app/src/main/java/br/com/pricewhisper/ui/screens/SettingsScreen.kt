package br.com.pricewhisper.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.pricewhisper.R
import br.com.pricewhisper.ui.theme.PriceWhisperTheme

enum class AppThemes(
    @StringRes val text: Int
) {
    LIGHT_MODE(R.string.app_theme_light),
    DARK_MODE(R.string.app_theme_dark)
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isDarkModeOn: MutableState<Boolean>,
    onSwitchThemeToLightMode: () -> Unit,
    onSwitchThemeToDarkMode: () -> Unit,
) {

    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            Text(
                text = stringResource(R.string.settings_topic_appearance_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider()
            Text(
                text = stringResource(R.string.settings_topic_appearance_subtopic_appearance_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.settings_topic_appearance_subtopic_appearance_about),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
            val isExpanded = remember { mutableStateOf(false) }
            val currentTheme = remember { mutableStateOf("") }

            if (isDarkModeOn.value)
                currentTheme.value = stringResource(AppThemes.DARK_MODE.text)
            else
                currentTheme.value = stringResource(AppThemes.LIGHT_MODE.text)

            AppThemeDropDown(
                expanded = isExpanded,
                selectedText = currentTheme,
                onSwitchThemeToLightMode = onSwitchThemeToLightMode,
                onSwitchThemeToDarkMode = onSwitchThemeToDarkMode
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppThemeDropDown(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,
    selectedText: MutableState<String>,
    onSwitchThemeToLightMode: () -> Unit,
    onSwitchThemeToDarkMode: () -> Unit,
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value }
    ) {
        TextField(
            value = selectedText.value,
            onValueChange = { selectedText.value = it },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
            },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            AppThemes.entries.forEach {
                val currentTheme = stringResource(it.text)
                DropdownMenuItem(
                    text = { Text(currentTheme) },
                    onClick = {
                        if (it == AppThemes.LIGHT_MODE) 
                            onSwitchThemeToLightMode()
                        else onSwitchThemeToDarkMode()
                        selectedText.value = currentTheme
                        expanded.value = false
                    }
                )
                if (it.ordinal != AppThemes.entries.lastIndex)
                    HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun SettingsPreview() {
    val isDarkModeOn = remember { mutableStateOf(false) }

    PriceWhisperTheme {
        Surface {
            SettingsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                isDarkModeOn = isDarkModeOn,
                onSwitchThemeToLightMode = {},
                onSwitchThemeToDarkMode = {}
            )
        }
    }
}