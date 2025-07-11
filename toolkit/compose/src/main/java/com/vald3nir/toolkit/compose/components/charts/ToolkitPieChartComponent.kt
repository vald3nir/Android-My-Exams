package com.vald3nir.toolkit.compose.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitPieChartComponent(label: String, data: List<Float>, colors: List<Color>, modifier: Modifier = Modifier) {
    val totalSum = data.sum()
    var startAngle = -90f

    Canvas(modifier = modifier.size(300.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val offsetX: Int = (canvasWidth / 3).toInt()
        val offsetY: Int = (canvasHeight / 4).toInt()

        data.forEachIndexed { index, value ->
            val sweepAngle = (value / totalSum) * 360f
            drawArc(color = colors[index], startAngle = startAngle, sweepAngle = sweepAngle, useCenter = true)
            startAngle += sweepAngle
        }

        drawIntoCanvas { canvas ->
            val paint = android.graphics.Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 80f
            }
            canvas.nativeCanvas.drawText(label, center.x - offsetX, center.y + offsetY, paint)
        }
    }
}