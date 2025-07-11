package com.vald3nir.toolkit.compose.components.calendar.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ToolkitSelectorMonthYearComponent(
    textColor: Color = Color.White,
    month: Int,
    year: Int,
    onMonthYearChange: (Int, Int) -> Unit
) {

    val months = (1..12).map {
        LocalDate.of(year, it, 1).month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {
            val newMonth = if (month == 1) 12 else month - 1
            val newYear = if (month == 1) year - 1 else year
            onMonthYearChange(newMonth, newYear)
        }) {
            Text(
                text = "<",
                fontSize = 20.sp,
                color = textColor
            )
        }

        Text(
            text = "${months[month - 1]} $year",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = textColor

        )

        IconButton(onClick = {
            val newMonth = if (month == 12) 1 else month + 1
            val newYear = if (month == 12) year + 1 else year
            onMonthYearChange(newMonth, newYear)
        }) {
            Text(
                text = ">",
                fontSize = 20.sp,
                color = textColor
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    var month by remember { mutableIntStateOf(LocalDate.now().monthValue) }
    var year by remember { mutableIntStateOf(LocalDate.now().year) }
    ToolkitSelectorMonthYearComponent(month = month, year = year, onMonthYearChange = { newMonth, newYear ->
        month = newMonth
        year = newYear
    })
}