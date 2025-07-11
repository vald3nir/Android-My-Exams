package com.vald3nir.toolkit.compose.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.BuildIcon
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.extensions.BuildLabel


@Composable
fun ToolkitInputPasswordComponent(
    modifier: Modifier = Modifier,
    inputValue: String = "",
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    colors: ScreenColorSchema,
    useTransparentBackend: Boolean = false,
    useTransparentBorder: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val backgroundColor = if (useTransparentBackend) Color.Transparent else colors.backgroundColor
    val borderColor = if (useTransparentBorder) Color.Transparent else colors.textColor
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.background(backgroundColor)) {
        if (label.isNotBlank()) {
            ToolkitText.Label(text = label, textColor = colors.textColor)
            MinSpaceHeight()
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

            // Texts
            value = inputValue,
            placeholder = placeholder.BuildLabel(colors.textColor),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            // Icons
            leadingIcon = ToolkitIcons.Password.BuildIcon(tint = colors.iconTint),
            trailingIcon = {
                ToolkitIcon(
                    imageVector = if (passwordVisible) ToolkitIcons.Visibility else ToolkitIcons.VisibilityOff,
                    tint = colors.iconTint,
                    onClick = { passwordVisible = !passwordVisible }
                )
            },

            // Colors
            textStyle = ToolkitText.labelStyle(textColor = colors.textColor),
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
        ToolkitInputPasswordComponent(
            colors = DefaultThemeColors().lightColors,
            inputValue = "Texto",
            errorValue = "Campo invalido"
        )
        DefaultSpaceHeight()
        ToolkitInputPasswordComponent(
            colors = DefaultThemeColors().darkColors,
            inputValue = "Texto",
            errorValue = "Campo invalido",
        )
    }
}