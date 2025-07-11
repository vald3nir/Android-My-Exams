package com.vald3nir.toolkit.compose.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.BigSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitDivider
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitSwitch
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.bigSpace
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema


sealed class ToolkitItemBottomSheet {
    data class Switch(
        val enable: Boolean,
        val title: String,
        val onCheckedChange: (Boolean) -> Unit,
    ) : ToolkitItemBottomSheet()

    data class Default(
        val icon: ImageVector? = null,
        val title: String,
        val onClick: () -> Unit,
    ) : ToolkitItemBottomSheet()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitBottomSheetComponent(
    items: List<ToolkitItemBottomSheet> = emptyList(),
    colors: ScreenColorSchema,
    onDismissRequest: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = colors.overlayColor
    ) {
        LazyColumn(modifier = Modifier.padding(vertical = defaultSpace)) {
            itemsIndexed(items) { index, item ->
                when (item) {
                    is ToolkitItemBottomSheet.Default -> DefaultItemBottomSheetComponent(colors = colors, item = item)
                    is ToolkitItemBottomSheet.Switch -> SwitchItemBottomSheetComponent(colors = colors, item = item)
                }
                if (index < items.lastIndex) {
                    ToolkitDivider(colors.dividerOverlayColor)
                }
            }
        }
        BigSpaceHeight()
    }
}

@Composable
private fun DefaultItemBottomSheetComponent(
    item: ToolkitItemBottomSheet.Default,
    colors: ScreenColorSchema,
    onDismissRequest: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(bigSpace)
            .background(colors.overlayColor)
            .fillMaxWidth()
            .clickable {
                item.onClick()
                onDismissRequest()
            }
            .padding(
                horizontal = defaultSpace,
                vertical = defaultSpace * 0.75f,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.icon?.let { icon ->
            ToolkitIcon(
                imageVector = icon,
                tint = colors.iconOverlayTint,
                modifier = Modifier.padding(end = defaultSpace * 0.5f)
            )
        }
        ToolkitText.Subtitle(
            text = item.title,
            textColor = colors.textOverlayColor
        )
    }
}

@Composable
private fun SwitchItemBottomSheetComponent(
    item: ToolkitItemBottomSheet.Switch,
    colors: ScreenColorSchema,
    onDismissRequest: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(bigSpace)
            .background(colors.overlayColor)
            .fillMaxWidth()
            .clickable {
                onDismissRequest()
            }
            .padding(
                horizontal = defaultSpace,
                vertical = defaultSpace * 0.75f,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolkitText.Subtitle(
            modifier = Modifier.weight(1f),
            text = item.title,
            textColor = colors.textOverlayColor
        )
        DefaultSpaceWidth()
        ToolkitSwitch(
            startEnable = item.enable,
            onCheckedChange = item.onCheckedChange,
            colors = colors
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val items: List<ToolkitItemBottomSheet> = listOf(
        ToolkitItemBottomSheet.Default(
            icon = ToolkitIcons.AccountCircle,
            title = "Profile",
            onClick = { }
        ),
        ToolkitItemBottomSheet.Switch(
            title = "Switch 1",
            enable = false,
            onCheckedChange = { }
        ),
        ToolkitItemBottomSheet.Switch(
            title = "Switch 2",
            enable = true,
            onCheckedChange = { }
        ),
        ToolkitItemBottomSheet.Default(
            icon = ToolkitIcons.Settings,
            title = "Settings",
            onClick = { }
        ),
        ToolkitItemBottomSheet.Default(
            title = "Logout",
            onClick = { }
        ),
    )
    LazyColumn(modifier = Modifier.padding(vertical = defaultSpace)) {
        val colors = DefaultThemeColors()
        itemsIndexed(items) { _, item ->
            when (item) {
                is ToolkitItemBottomSheet.Default -> DefaultItemBottomSheetComponent(colors = colors.lightColors, item = item)
                is ToolkitItemBottomSheet.Switch -> SwitchItemBottomSheetComponent(colors = colors.lightColors, item = item)
            }
        }
        itemsIndexed(items) { _, item ->
            when (item) {
                is ToolkitItemBottomSheet.Default -> DefaultItemBottomSheetComponent(colors = colors.darkColors, item = item)
                is ToolkitItemBottomSheet.Switch -> SwitchItemBottomSheetComponent(colors = colors.darkColors, item = item)
            }
        }
    }
}
