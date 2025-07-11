package com.vald3nir.toolkit.compose.components.toolbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitGenericToolbarComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    useRoundedBorder: Boolean = true,
    colors: ScreenColorSchema,
    onBackClick: (() -> Unit)? = null,
    onMenuClick: (() -> Unit)? = null,
    genericContent: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colors.toolbarBackgroundColor,
                shape = RoundedCornerShape(if (useRoundedBorder) 24.dp else 0.dp)
            )
            .padding(defaultSpace),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBackClick != null) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = colors.toolbarIconTint,
                modifier = Modifier.clickable { onBackClick() },
            )
        }
        if (onMenuClick != null) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = colors.toolbarIconTint,
                modifier = Modifier.clickable { onMenuClick() },
            )
        }

        DefaultSpaceWidth()

        ToolkitText.Label(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            text = title,
            textColor = colors.toolbarTextColor,
        )
        if (genericContent != {}) {
            genericContent()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors().darkColors
    Column(modifier = Modifier.padding(8.dp)) {
        ToolkitGenericToolbarComponent(
            colors = colors,
            title = "Toolbar Exemplo"
        )
        DefaultSpaceHeight()
        ToolkitGenericToolbarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onMenuClick = {})
        DefaultSpaceHeight()
        ToolkitGenericToolbarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onBackClick = {})
        DefaultSpaceHeight()
        ToolkitGenericToolbarComponent(
            colors = colors,
            title = "Toolbar Exemplo",
            onMenuClick = {},
            useRoundedBorder = false
        )
        DefaultSpaceHeight()
        ToolkitGenericToolbarComponent(
            colors = colors,
            title = "Texto longo com icone lateral",
            onBackClick = {},
            useRoundedBorder = false,
            genericContent = {
                ToolkitIcon(
                    imageVector = ToolkitIcons.AddChart,
                    tint = colors.toolbarIconTint
                )
            }
        )
    }
}