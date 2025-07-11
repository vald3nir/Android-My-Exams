package com.vald3nir.toolkit.compose.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.vald3nir.toolkit.compose.components.base.HalfSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.halfSpace
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema


data class ToolkitAutoCompleteInputData(val id: Long, val text: String) {
    fun contains(query: String): Boolean = text.contains(query, ignoreCase = true)
}

@Composable
fun ToolkitAutoCompleteInputComponent(
    suggestionList: List<ToolkitAutoCompleteInputData> = emptyList(),
    inputValue: String,
    placeholder: String = "",
    label: String = "",
    textAlign: TextAlign = TextAlign.Left,
    colors: ScreenColorSchema,
    useTransparentBackend: Boolean = false,
    startIcon: ImageVector? = null,
    onSelected: (String) -> Unit = {}
) {
    var itemNameSelected by remember { mutableStateOf(inputValue) }
    var expanded by remember { mutableStateOf(false) }
    var forceHideSuggestions by remember { mutableStateOf(false) }
    var filteredLabels by remember { mutableStateOf<List<ToolkitAutoCompleteInputData>>(emptyList()) }
    val backgroundColor = if (useTransparentBackend) Color.Transparent else colors.backgroundColor

    LaunchedEffect(itemNameSelected) {  // Updates suggestions without blocking the UI
        filteredLabels = if (itemNameSelected.isEmpty()) {
            emptyList()
        } else {
            suggestionList.filter { it.contains(itemNameSelected) }.take(5)
        }
        expanded = filteredLabels.isNotEmpty()
    }

    Box {
        Column {
            ToolkitInputTextComponent(
                inputValue = inputValue,
                useTransparentBackend = useTransparentBackend,
                textAlign = textAlign,
                colors = colors,
                label = label,
                placeholder = placeholder,
                singleLine = true,
                startIcon = startIcon,
                onValueChange = {
                    itemNameSelected = it
                    forceHideSuggestions = false
                    onSelected(itemNameSelected)
                }
            )
            if (expanded && filteredLabels.isNotEmpty() && !forceHideSuggestions) {
                AutoCompleteInputSuggestions(
                    backgroundColor = backgroundColor,
                    linkColor = colors.linkColor,
                    filteredLabels = filteredLabels,
                    onClickListener = {
                        itemNameSelected = it.text
                        forceHideSuggestions = true
                        expanded = false
                        filteredLabels = emptyList()
                        onSelected(it.text)
                    }
                )
            }
        }
    }
}

@Composable
private fun AutoCompleteInputSuggestions(
    backgroundColor: Color,
    linkColor: Color,
    filteredLabels: List<ToolkitAutoCompleteInputData> = emptyList(),
    onClickListener: (ToolkitAutoCompleteInputData) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalArrangement = Arrangement.spacedBy(halfSpace)
    ) {
        itemsIndexed(items = filteredLabels, itemContent = { _, suggestion ->
            HalfSpaceHeight()
            ToolkitText.Link(
                modifier = Modifier.clickable { onClickListener(suggestion) },
                text = suggestion.text,
                textColor = linkColor
            )
        })
    }
}