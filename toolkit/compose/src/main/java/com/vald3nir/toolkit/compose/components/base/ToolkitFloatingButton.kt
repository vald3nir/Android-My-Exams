package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitFloatingButton(
    colors: ScreenColorSchema,
    imageVector: ImageVector = ToolkitIcons.Add,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        modifier = Modifier.padding(defaultSpace),
        onClick = onClick,
        containerColor = colors.buttonBackgroundColor,
        contentColor = colors.buttonTextColor
    ) {
        ToolkitIcon(
            imageVector = imageVector,
            tint = colors.buttonTextColor,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    ToolkitFloatingButton(DefaultThemeColors().lightColors)
}

@Preview
@Composable
private fun PreviewDark() {
    ToolkitFloatingButton(DefaultThemeColors().darkColors)
}