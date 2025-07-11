package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitRow(
    modifier: Modifier = Modifier,
    paddingHorizontal: Dp = defaultSpace,
    paddingVertical: Dp = halfSpace,
    flagNoPadding: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit,
) {
    var paddingHorizontalValue = paddingHorizontal
    var paddingVerticalValue = paddingVertical
    if (flagNoPadding) {
        paddingHorizontalValue = 0.dp
        paddingVerticalValue = 0.dp
    }
    Row(
        modifier = modifier
            .padding(horizontal = paddingHorizontalValue, vertical = paddingVerticalValue)
            .fillMaxWidth(),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}