package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    elevation: Dp = 4.dp,
    backgroundColor: Color = Color.White,
    content: @Composable () -> Unit,
) {
    var cardModifier = modifier.fillMaxWidth()
    if (onClick != null) {
        cardModifier = cardModifier.clickable(enabled = true, onClick = { onClick() })
    }
    Card(
        modifier = cardModifier,
        shape = shape,
        elevation = elevation
    ) {
        Column(modifier = Modifier.background(backgroundColor)) {
            content()
        }
    }
}