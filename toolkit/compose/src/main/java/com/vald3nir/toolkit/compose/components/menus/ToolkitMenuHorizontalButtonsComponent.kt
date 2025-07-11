package com.vald3nir.toolkit.compose.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText

@Composable
fun ToolkitMenuHorizontalButtonsComponent(
    title: String,
    titleColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    options: List<String>,
    optionUnselectedTextColor: Color = Color.Black,
    optionSelectedTextColor: Color = Color.White,
    optionSelectedColor: Color = Color.Blue,
    optionUnselectedColor: Color = Color.LightGray,
    response: (String) -> Unit = {}
) {
    var selectedOption by remember { mutableStateOf(options.first()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        ToolkitText.Title(text = title, textColor = titleColor)
        DefaultSpaceHeight()
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                Button(
                    onClick = {
                        selectedOption = option
                        response(option)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) optionSelectedColor else optionUnselectedColor
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = option, color = if (selectedOption == option) optionSelectedTextColor else optionUnselectedTextColor)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ToolkitMenuHorizontalButtonsComponent(
        title = "Titulo",
        options = listOf("Opção 1", "Opção 2", "Opção 3")
    )
}