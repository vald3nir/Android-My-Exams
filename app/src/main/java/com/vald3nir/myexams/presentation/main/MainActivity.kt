package com.vald3nir.myexams.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.myexams.presentation.features.app.AppScreen
import com.vald3nir.toolkit.core.baseclasses.BaseActivity
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alterThemeColor(themaBrandEnum = ThemeBrandEnum.RED)
        setContent {
            ToolkitTheme(
                themeBrandEnum = themeSettings.themaBrandEnum,
                darkTheme = themeSettings.darkTheme,
                disableDynamicTheming = themeSettings.disableDynamicTheming,
            ) {
                AppScreen()
            }
        }
    }
}

internal fun Context.startMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
}