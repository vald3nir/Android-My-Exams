package com.vald3nir.toolkit.compose.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.R
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitEmptyStateScreen(
    title: String = stringResource(R.string.no_items_registered),
    imageVector: ImageVector,
    colors: ScreenColorSchema,
    onAddClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .background(colors.backgroundColor)
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(defaultSpace)
        ) {
            ToolkitIcon(
                imageVector = imageVector,
                modifier = Modifier.size(52.dp),
                tint = colors.iconTint
            )
            ToolkitText.Title(
                text = title,
                textColor = colors.textColor
            )
            if (onAddClick != null) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.overlayColor),
                    onClick = onAddClick,
                    content = {
                        ToolkitText.Label(
                            text = stringResource(R.string.btn_add),
                            textColor = colors.textOverlayColor
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    ToolkitEmptyStateScreen(
        imageVector = ToolkitIcons.Inbox,
        onAddClick = {},
        colors = DefaultThemeColors().lightColors
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    ToolkitEmptyStateScreen(
        imageVector = ToolkitIcons.Inbox,
        onAddClick = {},
        colors = DefaultThemeColors().darkColors
    )
}