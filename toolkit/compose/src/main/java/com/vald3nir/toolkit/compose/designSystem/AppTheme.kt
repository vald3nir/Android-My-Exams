package com.vald3nir.toolkit.compose.designSystem

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

val LocalAppColors = compositionLocalOf<ScreenColorSchema> {
    error("No AppColorScheme provided")
}

@Composable
fun AppTheme(
    themeViewModel: AppThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val colors = themeViewModel.currentTheme(context = LocalContext.current)
    CompositionLocalProvider(LocalAppColors provides colors) {
        content()
    }
}

@Composable
fun Context.loadAppColorSchema(): ScreenColorSchema {// todo valdenir testar internalizar o val context = LocalContext.current
    val appThemeViewModel: AppThemeViewModel = hiltViewModel()
    return appThemeViewModel.currentTheme(this)
}
