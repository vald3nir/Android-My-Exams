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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.BuildIcon
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.extensions.BuildLabel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun ToolkitMonetaryInputField(
    modifier: Modifier = Modifier, valueInCents: BigDecimal, placeholder: String = "", label: String = "", singleLine: Boolean = false, startIcon: ImageVector? = ToolkitIcons.Paid, endIcon: ImageVector? = ToolkitIcons.Edit, colors: ScreenColorSchema, useTransparentBackend: Boolean = false, useTransparentBorder: Boolean = false, onClickEndIcon: () -> Unit = {}, onValueChange: (Double) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val backgroundColor = if (useTransparentBackend) Color.Transparent else colors.backgroundColor
    val borderColor = if (useTransparentBorder) Color.Transparent else colors.textColor

    val trailingIcon: @Composable (() -> Unit)? = if (!valueInCents.isZero()) {
        ToolkitIcons.Close.BuildIcon(
            tint = colors.iconTint, onClick = { onValueChange(0.0) })
    } else endIcon?.BuildIcon(tint = colors.iconTint, onClick = onClickEndIcon)

    Column(modifier = modifier.background(backgroundColor)) {
        if (label.isNotBlank()) {
            ToolkitText.Label(text = label, textColor = colors.textColor)
            MinSpaceHeight()
        }
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }), onValueChange = {
                val digitsOnly = it.filter { c -> c.isDigit() }
                val valueL = digitsOnly.toLongOrNull() ?: 0L
                val newValue = valueL.toDouble() / 100
                onValueChange(newValue)
            },

            // Texts
            value = formatCurrency(valueInCents), placeholder = placeholder.BuildLabel(colors.textColor), singleLine = singleLine,

            // Icons
            leadingIcon = startIcon?.BuildIcon(tint = colors.iconTint), trailingIcon = trailingIcon,

            // Colors
            textStyle = LocalTextStyle.current.copy(color = colors.textColor), colors = OutlinedTextFieldDefaults.colors(
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
    }
}

private fun BigDecimal.isZero() = this == BigDecimal.ZERO

private fun formatCurrency(valueInCents: BigDecimal): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).apply {
        currency = Currency.getInstance("BRL")
    }
    val value = valueInCents.divide(BigDecimal("100")).setScale(4, RoundingMode.HALF_UP)
    return format.format(value)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val valueInCents by remember { mutableStateOf(BigDecimal("2.99")) }
    Column {
        ToolkitMonetaryInputField(
            valueInCents = valueInCents,
            colors = DefaultThemeColors().lightColors,
            label = "Valor",
            placeholder = "Digite o valor",
            startIcon = ToolkitIcons.Paid,
            endIcon = ToolkitIcons.Edit,
        )
        DefaultSpaceHeight()
        ToolkitMonetaryInputField(
            valueInCents = valueInCents,
            colors = DefaultThemeColors().darkColors,
            label = "Valor",
            placeholder = "Digite o valor",
            startIcon = ToolkitIcons.Paid,
            endIcon = ToolkitIcons.Edit,
        )
    }
}