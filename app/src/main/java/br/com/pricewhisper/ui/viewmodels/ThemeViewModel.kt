package br.com.pricewhisper.ui.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

private const val SHARED_PREFS_PALETTE_KEY = "IS_DARK_MODE_ON"

/**
 * ViewModel class for managing theme-related operations.
 */
class ThemeViewModel : ViewModel() {
    val currentPrefs: MutableState<SharedPreferences?> = mutableStateOf(null)
    val isDarkModeOn: MutableState<Boolean> = mutableStateOf(false)

    init {
        currentPrefs.value = null
        isDarkModeOn.value = false
    }

    /**
     * Retrieves the current palette mode from SharedPreferences.
     *
     * @return Boolean indicating whether dark mode is enabled.
     */
    fun getCurrentPalette(): Boolean {
        return currentPrefs.value
            ?.getBoolean(SHARED_PREFS_PALETTE_KEY, false)
            ?: false
    }

    /**
     * Switches the palette mode and updates SharedPreferences.
     */
    fun switchPalette() {
        currentPrefs.value
            ?.edit()
            ?.putBoolean(SHARED_PREFS_PALETTE_KEY, isDarkModeOn.value)
            ?.apply()
        isDarkModeOn.value = getCurrentPalette()
    }
}
