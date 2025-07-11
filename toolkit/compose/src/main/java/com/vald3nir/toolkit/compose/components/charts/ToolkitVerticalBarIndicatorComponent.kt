package com.vald3nir.toolkit.compose.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitVerticalBarIndicatorComponent(modifier: Modifier, maxPower: Int) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasHeight = size.height
            val numberOfTicks = 5
            val tickSpacing = (maxPower / numberOfTicks)
            val tickHeight = (canvasHeight) / numberOfTicks
            for (i in 0..numberOfTicks) {
                val yPosition = canvasHeight - (i * tickHeight)
                drawLine(
                    color = Color.White,
                    start = androidx.compose.ui.geometry.Offset(0f, yPosition),
                    end = androidx.compose.ui.geometry.Offset(20f, yPosition),
                    strokeWidth = 2f
                )
                drawIntoCanvas { canvas ->
                    val paint = android.graphics.Paint().apply {
                        textSize = 24f
                        color = android.graphics.Color.WHITE
                    }
                    canvas.nativeCanvas.drawText(
                        "${i * tickSpacing}",
                        30f,
                        yPosition + 10f,
                        paint
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitVerticalBarIndicatorComponent(
                maxPower = 100,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(300.dp)
                    .width(25.dp),
            )
        }
    }
}