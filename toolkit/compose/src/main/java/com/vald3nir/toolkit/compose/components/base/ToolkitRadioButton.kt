package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitRadioButton(
    label: String,
    modifier: Modifier = Modifier,
    colors: ScreenColorSchema,
    checked: Boolean,
    onClick: () -> Unit = {},
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            modifier = modifier,
            selected = checked,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.checkedColor,
                unselectedColor = colors.uncheckedColor
            )
        )
        ToolkitText.Label(
            text = label,
            textColor = colors.textOverlayColor,
            modifier = modifier.clickable { onClick() }
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors()
    Row {
        ToolkitRadioButton(label = "label", colors = colors.lightColors, checked = false)
        ToolkitRadioButton(label = "label", colors = colors.lightColors, checked = true)
        ToolkitRadioButton(label = "label", colors = colors.darkColors, checked = false)
        ToolkitRadioButton(label = "label", colors = colors.darkColors, checked = true)
    }
}