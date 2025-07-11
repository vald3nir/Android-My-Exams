package com.vald3nir.toolkit.compose.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.BuildIcon
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.extensions.BuildLabel

@Composable
fun ToolkitSearchFieldComponent(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    placeholder: String = "Pesquisar",
    colors: ScreenColorSchema,
    useUnderline: Boolean = false,
    onQueryChange: (String) -> Unit = {},
) {

    val inputTextModifier = modifier
        .fillMaxWidth()
        .then(
            if (useUnderline) {
                Modifier.background(Color.Transparent)
            } else {
                // Caso contrário, mantém o fundo
                Modifier.background(colors.backgroundColor, shape = RoundedCornerShape(24.dp))
            }
        )

    val trailingIcon: @Composable (() -> Unit)? = if (searchQuery.isNotEmpty()) {
        ToolkitIcons.Close.BuildIcon(
            tint = colors.iconTint,
            onClick = { onQueryChange("") }
        )
    } else null

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = inputTextModifier,

            // Listeners
            onValueChange = onQueryChange,

            // Texts
            value = searchQuery,
            placeholder = placeholder.BuildLabel(colors.textColor),
            textStyle = ToolkitText.labelStyle(colors.textColor),
            singleLine = true,

            // Icons
            leadingIcon = ToolkitIcons.Search.BuildIcon(
                tint = colors.iconTint,
                onClick = { onQueryChange("") }
            ),
            trailingIcon = trailingIcon,

            // Colors and Shapes
            shape = RoundedCornerShape(0.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            )
        )
        if (useUnderline) {
            Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(modifier = Modifier.padding(8.dp)) {
        ToolkitSearchFieldComponent(colors = DefaultThemeColors().lightColors)
        DefaultSpaceHeight()
        ToolkitSearchFieldComponent(colors = DefaultThemeColors().darkColors)
        DefaultSpaceHeight()
        ToolkitSearchFieldComponent(colors = DefaultThemeColors().lightColors, searchQuery = "Texto de exemplo")
        DefaultSpaceHeight()
        ToolkitSearchFieldComponent(colors = DefaultThemeColors().lightColors, searchQuery = "Texto de exemplo", useUnderline = true)
        DefaultSpaceHeight()
    }
}