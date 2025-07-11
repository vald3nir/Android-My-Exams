package com.vald3nir.toolkit.compose.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.calendar.selectors.ToolkitSelectorMonthYearComponent
import com.vald3nir.toolkit.helpers.utils.getSizeDaysInMonth
import java.time.LocalDate

@Composable
fun ToolkitCalendarComponent(
    modifier: Modifier = Modifier,
    month: Int,
    year: Int,
    selectedDay: Int = -1,
    labels: MutableList<String> = emptyList<String>().toMutableList(),
    onMonthYearChanged: (Int, Int) -> Unit = { newMonth, newYear -> },
    onDaySelected: (Int) -> Unit = {}
) {
    val totalDays = getSizeDaysInMonth(month, year)
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value % 7 // 0 for sunday
    val days = List(firstDayOfWeek) { "" } + (1..totalDays).map { it.toString() }

    if (labels.size < totalDays) {
        labels.addAll(List(totalDays - labels.size) { "" })
    }

    Column(modifier = modifier.padding(8.dp)) {
        ToolkitSelectorMonthYearComponent(
            month = month,
            year = year,
            onMonthYearChange = onMonthYearChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        ToolkitCalendarRowDayOfWeekComponent()
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = modifier
        ) {
            items(days.size) { index ->
                val day = days[index]
                if (day.isEmpty()) return@items
                ToolkitCalendarCellComponent(
                    day = day,
                    text = labels[day.toInt() - 1],
                    selectedDay = selectedDay,
                    onDaySelected = onDaySelected
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ToolkitCalendarComponent(
        month = 2,
        year = 2025,
        selectedDay = 5,
        modifier = Modifier.fillMaxWidth()
    )
}