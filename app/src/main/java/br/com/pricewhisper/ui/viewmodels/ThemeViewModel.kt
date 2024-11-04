package br.com.pricewhisper.ui.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

private const val SHARED_PREFS_PALETTE_KEY = "IS_DARK_MODE_ON"

class ThemeViewModel : ViewModel() {
    val currentPrefs: MutableState<SharedPreferences?> = mutableStateOf(null)
    val isDarkModeOn: MutableState<Boolean> = mutableStateOf(false)

    init {
        currentPrefs.value = null
        isDarkModeOn.value = false
    }

    fun getCurrentPalette(): Boolean {
        return currentPrefs.value
            ?.getBoolean(SHARED_PREFS_PALETTE_KEY, false)
            ?: false
    }

    fun switchPalette() {
        currentPrefs.value
            ?.edit()
            ?.putBoolean(SHARED_PREFS_PALETTE_KEY, isDarkModeOn.value)
            ?.apply()
        isDarkModeOn.value = getCurrentPalette()
    }
}
