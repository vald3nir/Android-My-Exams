package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitCheckBox(
    modifier: Modifier = Modifier,
    colors: ScreenColorSchema,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Checkbox(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = colors.checkedColor,
            checkmarkColor = colors.checkmarkColor,
            uncheckedColor = colors.uncheckedColor,
            disabledColor = colors.disabledColor,
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors()
    Row {
        ToolkitCheckBox(colors = colors.lightColors, checked = false)
        ToolkitCheckBox(colors = colors.lightColors, checked = true)
        ToolkitCheckBox(colors = colors.darkColors, checked = false)
        ToolkitCheckBox(colors = colors.darkColors, checked = true)
    }
}