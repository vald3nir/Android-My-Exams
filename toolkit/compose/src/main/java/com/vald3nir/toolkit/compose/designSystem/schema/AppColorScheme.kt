package com.vald3nir.toolkit.compose.designSystem.schema

data class AppColorScheme(
    val lightColorScheme: ScreenColorSchema,
    val darkColorScheme: ScreenColorSchema,
    val isDarkMode: Boolean = false
)