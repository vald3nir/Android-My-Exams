package com.vald3nir.myexams.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.myexams.presentation.features.onboarding.OnboardingScreen
import com.vald3nir.toolkit.core.baseclasses.BaseActivity
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import dagger.hilt.android.AndroidEntryPoint

/*
Esta Activity é responsável por realizar as verificações iniciais do aplicativo, incluindo a autenticação do usuário
e a identificação e preenchimento de informações pendentes no seu perfil.
*/

@AndroidEntryPoint
class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alterThemeColor(themaBrandEnum = ThemeBrandEnum.RED)
        setContent {
            ToolkitTheme(
                themeBrandEnum = themeSettings.themaBrandEnum,
                darkTheme = themeSettings.darkTheme,
                disableDynamicTheming = themeSettings.disableDynamicTheming,
            ) {
                OnboardingScreen()
            }
        }
    }
}

internal fun Context.startOnboardingActivity() {
    val intent = Intent(this, OnboardingActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
}