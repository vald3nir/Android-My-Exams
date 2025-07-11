package com.vald3nir.toolkit.compose.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.BuildIcon
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.extensions.BuildLabel


@Composable
fun ToolkitInputTextComponent(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    inputValue: String = "",
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    textAlign: TextAlign = TextAlign.Left,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = ToolkitIcons.Edit,
    colors: ScreenColorSchema,
    useTransparentBackend: Boolean = false,
    useTransparentBorder: Boolean = false,
    onClickEndIcon: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val backgroundColor = if (useTransparentBackend) Color.Transparent else colors.backgroundColor
    val borderColor = if (useTransparentBorder) Color.Transparent else colors.textColor

    val trailingIcon: @Composable (() -> Unit)? = if (inputValue.isNotEmpty()) {
        ToolkitIcons.Close.BuildIcon(
            tint = colors.iconTint,
            onClick = { onValueChange("") }
        )
    } else endIcon?.BuildIcon(tint = colors.iconTint, onClick = onClickEndIcon)

    Column(modifier = modifier.background(backgroundColor)) {
        if (label.isNotBlank()) {
            ToolkitText.Label(text = label, textColor = colors.textColor)
            MinSpaceHeight()
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

            // Texts
            value = inputValue,
            placeholder = placeholder.BuildLabel(colors.textColor),
            singleLine = singleLine,

            // Icons
            leadingIcon = startIcon?.BuildIcon(tint = colors.iconTint),
            trailingIcon = trailingIcon,

            // Colors
            textStyle = LocalTextStyle.current.copy(
                color = colors.textColor,
                textAlign = textAlign
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                cursorColor = colors.textColor,
                selectionColors = TextSelectionColors(handleColor = colors.textColor, backgroundColor = colors.textColor),
                focusedLabelColor = colors.textColor,
                unfocusedLabelColor = colors.textColor,
                focusedTextColor = colors.textColor,
                unfocusedTextColor = colors.textColor,
                focusedPlaceholderColor = colors.textColor,
                unfocusedPlaceholderColor = colors.textColor.copy(alpha = 0.5f),
                disabledPlaceholderColor = colors.textColor.copy(alpha = 0.5f),
            )
        )
        if (!errorValue.isNullOrBlank()) {
            MinSpaceHeight()
            ToolkitText.Label(text = errorValue, textColor = colors.warningTextColor)
            MinSpaceHeight()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun Preview() {
    Column {
        ToolkitInputTextComponent(
            colors = DefaultThemeColors().lightColors,
            label = "label",
            placeholder = "placeholder",
            startIcon = ToolkitIcons.Favorite,
            endIcon = ToolkitIcons.Edit,
        )
        DefaultSpaceHeight()
        ToolkitInputTextComponent(
            colors = DefaultThemeColors().darkColors,
            label = "label",
            placeholder = "placeholder",
            startIcon = ToolkitIcons.Favorite,
            endIcon = ToolkitIcons.Edit,
        )
        DefaultSpaceHeight()
        ToolkitInputTextComponent(
            colors = DefaultThemeColors().lightColors,
            inputValue = "Texto",
            errorValue = "Campo invalido",
            startIcon = ToolkitIcons.Favorite,
            endIcon = ToolkitIcons.Edit,
        )
        DefaultSpaceHeight()
        ToolkitInputTextComponent(
            colors = DefaultThemeColors().darkColors,
            inputValue = "Texto",
            errorValue = "Campo invalido",
            startIcon = ToolkitIcons.Favorite,
            endIcon = ToolkitIcons.Edit,
        )
    }
}