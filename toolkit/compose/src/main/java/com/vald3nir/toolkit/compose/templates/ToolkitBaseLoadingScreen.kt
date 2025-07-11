package com.vald3nir.toolkit.compose.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText

@Composable
fun ToolkitBaseLoadingScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black.copy(alpha = 0.75f),
    foregroundColor: Color = Color.White,
    message: String = "Loading...",
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = foregroundColor,
                modifier = Modifier.size(48.dp),
                strokeWidth = 4.dp
            )
            DefaultSpaceHeight()
            ToolkitText.Title(text = message, textColor = foregroundColor)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ToolkitBaseLoadingScreen()
}