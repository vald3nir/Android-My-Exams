package com.vald3nir.myexams.di

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.designSystem.preference.ThemePreferences
import com.vald3nir.toolkit.compose.designSystem.schema.AppColorScheme
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideThemePreferences(@ApplicationContext context: Context): ThemePreferences {
        return ThemePreferences(context)
    }

    @Provides
    @Singleton
    fun provideAppColorScheme(@ApplicationContext context: Context, themePreferences: ThemePreferences): AppColorScheme {
        val colors = appMyExamsTheme()
        return AppColorScheme(lightColorScheme = colors, darkColorScheme = colors, isDarkMode = themePreferences.isDarkMode(context))
    }
}

internal const val AppPrimaryColorInt = 0xFFAD032C

private val AppPrimaryColor = Color(0xFFAD032C)
private val AppSecondaryColor = Color(0xFFE5335C)
private val AppTertiaryColor = Color.White

internal fun appMyExamsTheme() = ScreenColorSchema(
    backgroundColor = AppTertiaryColor,
    textColor = Color.Black,
    warningTextColor = Color.Yellow,
    linkColor = Color.Red,
    iconTint = AppPrimaryColor,

    overlayColor = AppSecondaryColor,
    textOverlayColor = Color.White,
    linkOverlayColor = Color.Yellow,
    dividerOverlayColor = Color.White,
    iconOverlayTint = Color.White,

    buttonBackgroundColor = AppPrimaryColor,
    buttonBackgroundDisableColor = Color.LightGray,
    buttonTextColor = Color.White,

    toolbarBackgroundColor = AppSecondaryColor,
    toolbarTextColor = Color.White,
    toolbarIconTint = Color.White,

    checkedColor =  Color.White,
    checkmarkColor = Color.White,
    uncheckedColor = Color.Gray,
    disabledColor = Color.Gray,

    dialogBackgroundColor = Color.White,
    dialogTextColor = Color.Black,
    dialogConfirmBtnTextColor = Color.White,
    dialogConfirmBtnBackgroundColor = Color.Blue,
    dialogCancelBtnTextColor = Color.Black,
    dialogCancelBtnBackgroundColor = Color.White,

    switchEnableColor = Color.Blue,
    switchDisableColor = Color.Gray,
)