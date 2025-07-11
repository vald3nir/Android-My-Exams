package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

object ToolkitText {

    @Composable
    fun BigTitle(
        modifier: Modifier = Modifier,
        text: String, textColor:
        Color = Color.Black,
        textAlign: TextAlign = TextAlign.Companion.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Companion.Bold,
                color = textColor,
                textAlign = textAlign
            )
        )
    }

    @Composable
    fun Title(
        modifier: Modifier = Modifier,
        text: String,
        textColor: Color = Color.Black,
        textAlign: TextAlign = TextAlign.Companion.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Companion.Bold,
                color = textColor,
                textAlign = textAlign
            )
        )
    }

    @Composable
    fun Subtitle(
        modifier: Modifier = Modifier,
        text: String,
        textColor: Color = Color.DarkGray,
        textAlign: TextAlign = TextAlign.Companion.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Companion.SemiBold,
                color = textColor,
                textAlign = textAlign
            )
        )
    }

    @Composable
    fun Label(
        modifier: Modifier = Modifier,
        text: String,
        textColor: Color = Color.Gray,
        textAlign: TextAlign = TextAlign.Companion.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = labelStyle(textColor),
            textAlign = textAlign
        )
    }

    fun labelStyle(textColor: Color = Color.Gray) = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Companion.Normal,
        color = textColor
    )

    @Composable
    fun Link(
        modifier: Modifier = Modifier,
        text: String,
        textColor: Color = Color.Blue,
        textAlign: TextAlign = TextAlign.Companion.Start
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Companion.Normal,
                color = textColor,
                textDecoration = TextDecoration.Companion.Underline,
                textAlign = textAlign
            )
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    ShowContent(DefaultThemeColors().lightColors)
}

@Preview
@Composable
private fun PreviewDark() {
    ShowContent(DefaultThemeColors().darkColors)
}

@Composable
private fun ShowContent(colors: ScreenColorSchema) {
    Column(modifier = Modifier.background(colors.backgroundColor)) {
        ToolkitText.BigTitle(text = "BigTitle", textColor = colors.textColor)
        ToolkitText.Title(text = "Title", textColor = colors.textColor)
        ToolkitText.Subtitle(text = "Subtitle", textColor = colors.textColor)
        ToolkitText.Label(text = "Paragraph", textColor = colors.textColor)
        ToolkitText.Link(text = "Link", textColor = colors.linkColor)
    }
}