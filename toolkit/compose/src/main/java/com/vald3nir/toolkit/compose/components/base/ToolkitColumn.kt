package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitColumn(
    modifier: Modifier = Modifier,
    paddingHorizontal: Dp = defaultSpace,
    paddingVertical: Dp = halfSpace,
    flagNoPadding: Boolean = false,
    flagEnableScroll: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    var paddingHorizontalValue = paddingHorizontal
    var paddingVerticalValue = paddingVertical
    if (flagNoPadding) {
        paddingHorizontalValue = 0.dp
        paddingVerticalValue = 0.dp
    }
    var columnModifier = modifier
        .padding(horizontal = paddingHorizontalValue, vertical = paddingVerticalValue)
        .fillMaxWidth()

    if (flagEnableScroll) {
        columnModifier = columnModifier.verticalScroll(rememberScrollState())
    }
    Column(modifier = columnModifier, content = content)
}