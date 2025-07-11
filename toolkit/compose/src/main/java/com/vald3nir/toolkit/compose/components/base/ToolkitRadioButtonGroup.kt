package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

enum class ToolkitRadioButtonGroupType { LIST, GRID }

@Composable
fun ToolkitRadioButtonGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedValue: String? = null,
    viewType: ToolkitRadioButtonGroupType,
    colors: ScreenColorSchema,
    columnsSize: Int = 2, // for grid
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember { mutableStateOf(selectedValue) }

    when (viewType) {
        ToolkitRadioButtonGroupType.LIST -> {
            LazyColumn(modifier = modifier) {
                items.forEach { item ->
                    item {
                        ToolkitRadioButton(
                            label = item,
                            checked = selectedItem == item,
                            colors = colors,
                            onClick = {
                                selectedItem = item
                                onItemSelected(item)
                            }
                        )
                    }
                }
            }
        }

        ToolkitRadioButtonGroupType.GRID -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnsSize),
                modifier = modifier
            ) {
                items.forEach { item ->
                    item {
                        ToolkitRadioButton(
                            label = item,
                            checked = selectedItem == item,
                            colors = colors,
                            onClick = {
                                selectedItem = item
                                onItemSelected(item)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    ToolkitRadioButtonGroup(
        items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"),
        viewType = ToolkitRadioButtonGroupType.GRID,
        colors = DefaultThemeColors().lightColors,
    )
}