package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentExamFieldCell(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    errorMessage: String? = null,
    colors: ScreenColorSchema,
) {
    Column(modifier = modifier.background(colors.overlayColor)) {
        MinSpaceHeight()
        ToolkitText.Label(modifier = Modifier, text = label, textColor = colors.textOverlayColor)
        MinSpaceHeight()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (!errorMessage.isNullOrBlank()) colors.warningTextColor else colors.dividerOverlayColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            ToolkitText.Label(
                text = value,
                textColor = colors.textOverlayColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        MinSpaceHeight()
        if (!errorMessage.isNullOrBlank()) {
            ToolkitText.Label(
                modifier = Modifier,
                text = errorMessage,
                textColor = colors.warningTextColor
            )
            MinSpaceHeight()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    val colors = appMyExamsTheme()
    Column {
        ComponentExamFieldCell(
            colors = colors,
            label = "label",
            value = "value",
            errorMessage = "Este é um error message"
        )
        DefaultSpaceHeight()
        ComponentExamFieldCell(
            colors = colors,
            label = "label",
            value = "value",
        )
    }
}