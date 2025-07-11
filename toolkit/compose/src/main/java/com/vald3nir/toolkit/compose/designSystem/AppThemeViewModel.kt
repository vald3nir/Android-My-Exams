package com.vald3nir.toolkit.compose.designSystem

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vald3nir.toolkit.compose.designSystem.preference.ThemePreferences
import com.vald3nir.toolkit.compose.designSystem.schema.AppColorScheme
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppThemeViewModel @Inject constructor(
    private val appColorSchema: AppColorScheme,
    private val themePreferences: ThemePreferences,
) : ViewModel() {

    fun isDarkMode(context: Context) = themePreferences.isDarkMode(context)

    fun updateTheme(isDarkMode: Boolean) {
        themePreferences.saveThemeMode(isDarkMode)
    }

    fun currentTheme(context: Context): ScreenColorSchema {
        return if (themePreferences.isDarkMode(context)) {
            appColorSchema.darkColorScheme
        } else {
            appColorSchema.lightColorScheme
        }
    }
}