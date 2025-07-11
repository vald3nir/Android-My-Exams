package com.vald3nir.toolkit.compose.components.charts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.abs

@Composable
fun ToolkitSpeedometerComponent(currentConsumption: Float, maxRelativeConsumption: Float, modifier: Modifier = Modifier) {
    val maxValue = kotlin.math.max(currentConsumption, maxRelativeConsumption)
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        ToolkitPieChartComponent(
            label = "${currentConsumption.toInt()} kWh",
            data = listOf(currentConsumption, abs(maxValue - currentConsumption)),
            colors = listOf(Color.Red, Color.Gray)
        )
    }
}