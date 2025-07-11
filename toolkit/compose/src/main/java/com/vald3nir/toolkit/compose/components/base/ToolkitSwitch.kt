package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitSwitch(
    startEnable: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: ScreenColorSchema,
) {
    Switch(
        checked = startEnable,
        onCheckedChange = { onCheckedChange(it) },
        colors = SwitchDefaults.colors(
            checkedThumbColor = colors.switchEnableColor,
            uncheckedThumbColor = colors.switchDisableColor,
            checkedTrackColor = colors.switchEnableColor.copy(alpha = 0.5f),
            uncheckedTrackColor = colors.switchDisableColor.copy(alpha = 0.5f)
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(modifier = Modifier.padding(vertical = defaultSpace)) {
        val colors = DefaultThemeColors()
        ToolkitSwitch(startEnable = true, onCheckedChange = {}, colors = colors.lightColors)
        ToolkitSwitch(startEnable = false, onCheckedChange = {}, colors = colors.lightColors)
        ToolkitSwitch(startEnable = true, onCheckedChange = {}, colors = colors.darkColors)
        ToolkitSwitch(startEnable = false, onCheckedChange = {}, colors = colors.darkColors)
    }
}