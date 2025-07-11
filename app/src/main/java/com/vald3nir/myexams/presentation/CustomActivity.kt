package com.vald3nir.myexams.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.AppPrimaryColorInt
import com.vald3nir.toolkit.compose.extensions.updateStatusBarColor
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity

internal abstract class CustomActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (BuildConfig.FLAVOR == "dev") {
            updateStatusBarColor(statusBarColor = getColor(R.color.ic_launcher_background))
        } else {
            updateStatusBarColor(statusBarColor = AppPrimaryColorInt.toInt())
        }
    }
}