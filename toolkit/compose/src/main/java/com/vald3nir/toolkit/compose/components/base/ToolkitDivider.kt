package com.vald3nir.toolkit.compose.components.base

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitDivider(color: Color) {
    Divider(thickness = 0.5.dp, color = color)
}