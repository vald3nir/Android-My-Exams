package com.vald3nir.toolkit.compose.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.WindowInsets
import androidx.core.view.WindowInsetsControllerCompat
import com.vald3nir.toolkit.compose.designSystem.preference.isSystemDarkMode

fun Activity.updateStatusBarColor() {
    updateStatusBarColor(if (isSystemDarkMode()) Color.BLACK else Color.WHITE)
}

fun Activity.updateStatusBarColor(statusBarColor: Int) {
    // WindowCompat.setDecorFitsSystemWindows(window, false) // BUG: for some devices it creates extra spacing, for others it removes it.
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(statusBarColor)
            // Adjust padding to avoid overlap
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }
    } else {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = statusBarColor
    }
}