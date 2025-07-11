package com.vald3nir.toolkit.compose.components.charts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.helpers.utils.getRandomColors

data class ToolkitBarChartItem(val value: Float, val label: String)

@Composable
fun ToolkitBarChartComponent(
    data: List<ToolkitBarChartItem>,
    colors: List<Color>,
    textColor: Color = Color.White,
    onClickBar: (index: Int) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val maxValue = data.maxOf { it.value }
    val maxHeight = 300.dp

    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            ToolkitVerticalBarIndicatorComponent(
                maxPower = maxValue.toInt(),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .height(250.dp)
                    .width(20.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(maxHeight)
                    .padding(start = 35.dp)
                    .horizontalScroll(scrollState)
            ) {
                data.forEachIndexed { index, dataPoint ->
                    ToolkitVerticalBarComponent(
                        textColor = textColor,
                        barColor = colors[index % colors.size],
                        barHeight = 250.dp.times((dataPoint.value / maxValue)),
                        maxHeight = maxHeight,
                        label = dataPoint.label,
                        value = dataPoint.value.toString(),
                        onClick = { onClickBar(index) }
                    )
                    if (index < data.size - 1) {
                        DefaultSpaceWidth()
                    }
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
            val data = listOf(
                ToolkitBarChartItem(750f, "Jan"),
                ToolkitBarChartItem(200f, "Fev"),
                ToolkitBarChartItem(300f, "Mar"),
            )
            ToolkitBarChartComponent(
                data = data,
                colors = getRandomColors(data.size)
            )
        }
    }
}