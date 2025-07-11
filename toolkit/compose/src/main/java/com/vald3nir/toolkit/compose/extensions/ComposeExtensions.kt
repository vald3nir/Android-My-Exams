package com.vald3nir.toolkit.compose.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.vald3nir.toolkit.compose.components.base.ToolkitText


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ScreenSizeInDp(): Pair<Float, Float> {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    return Pair(screenWidthDp.toFloat(), screenHeightDp.toFloat())
}

@Composable
fun String.BuildLabel(textColor: Color): @Composable () -> Unit = {
    ToolkitText.Label(text = this, textColor = textColor)
}

