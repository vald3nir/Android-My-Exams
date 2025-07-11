package com.vald3nir.toolkit.compose.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ToolkitVerticalBarComponent(
    label: String,
    value: String,
    textColor: Color = Color.White,
    maxHeight: Dp = 300.dp,
    barHeight: Dp = 150.dp,
    textSize: Dp = 24.dp,
    barColor: Color,
    barWidth: Dp = 48.dp,
    onClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .height(maxHeight)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(textSize),
            text = value,
            color = textColor
        )
        Canvas(modifier = Modifier.size(barWidth, barHeight)) {
            val barSize = androidx.compose.ui.geometry.Size(barWidth.toPx(), barHeight.toPx())
            drawRect(
                color = barColor,
                size = barSize
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(textSize),
            text = label,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Surface(
        color = Color.Black
    ) {
        ToolkitVerticalBarComponent(
            barColor = Color.Blue,
            label = "fev",
            value = "150"
        )
    }
}