package com.vald3nir.toolkit.compose.designSystem.preference

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit

class ThemePreferences(context: Context) {

    private val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    fun saveThemeMode(isDarkMode: Boolean) {
        val mode: ThemeMode = if (isDarkMode) ThemeMode.DARK else ThemeMode.LIGHT
        prefs.edit { putString("theme_mode", mode.name) }
    }

    fun getThemeModeSaved(): ThemeMode {
        val name = prefs.getString("theme_mode", ThemeMode.SYSTEM.name)
        return ThemeMode.valueOf(name ?: ThemeMode.SYSTEM.name)
    }

    fun isDarkMode(context: Context): Boolean {
        val themeMode = getThemeModeSaved()
        val isDarkMode = when (themeMode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            ThemeMode.SYSTEM -> context.isSystemDarkMode()
        }
        return isDarkMode
    }
}

fun Context.isSystemDarkMode(): Boolean {
    val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
}