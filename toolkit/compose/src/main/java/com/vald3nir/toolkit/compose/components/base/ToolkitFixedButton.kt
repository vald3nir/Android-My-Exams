package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitFixedButton(
    label: String,
    showLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    colors: ScreenColorSchema,
) {
    Column(modifier = Modifier.background(colors.backgroundColor)) {
        Button(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colors.buttonBackgroundColor,
                disabledBackgroundColor = colors.buttonBackgroundDisableColor,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.backgroundColor)
                .padding(halfSpace),
            content = {
                Column(modifier = Modifier.padding(halfSpace)) {
                    if (showLoading) {
                        CircularProgressIndicator(
                            color = colors.buttonTextColor,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        ToolkitText.Subtitle(text = label, textColor = colors.buttonTextColor)
                    }
                }
            }
        )
        DefaultSpaceHeight()
    }
}

@Preview
@Composable
private fun Preview() {
    val colors = DefaultThemeColors()
    Column {
        ToolkitFixedButton(colors = colors.lightColors, label = "Meu Botão")
        DefaultSpaceHeight()
        ToolkitFixedButton(colors = colors.darkColors, label = "Meu Botão")
    }
}