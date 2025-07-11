package com.vald3nir.toolkit.compose.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth

@Composable
fun ToolkitCalendarCellComponent(
    day: String,
    text: String = "X",
    selectedDay: Int,
    onDaySelected: (Int) -> Unit = {}
) {
    val textColor = if (isDaySelected(day, selectedDay)) Color.White else Color.Black
    val backgroundColor = if (isDaySelected(day, selectedDay)) Color.Blue else Color.LightGray

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = day,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.size(20.dp)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable(enabled = day.isNotEmpty()) {
                    if (day.isNotEmpty()) onDaySelected(day.toInt())
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }
}

@Composable
private fun isDaySelected(day: String, selectedDay: Int) = day.isNotEmpty() && (day.toInt() == selectedDay)


@Preview
@Composable
private fun Preview() {
    Row {
        ToolkitCalendarCellComponent(
            day = "5",
            selectedDay = 5,
        )
        DefaultSpaceWidth()
        ToolkitCalendarCellComponent(
            day = "5",
            selectedDay = 1,
        )
    }
}