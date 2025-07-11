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

@Composable
fun ToolkitSelectorYearComponent(
    textColor: Color = Color.White,
    year: Int,
    onYearChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {
            onYearChange(year - 1)
        }) {
            Text(
                text = "<",
                fontSize = 20.sp,
                color = textColor
            )
        }

        Text(
            text = "$year",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = textColor

        )

        IconButton(onClick = {
            onYearChange(year + 1)
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
    var year by remember { mutableIntStateOf(LocalDate.now().year) }
    ToolkitSelectorYearComponent(year = year, onYearChange = { newYear ->
        year = newYear
    })
}