package com.vald3nir.toolkit.compose.components.navigator

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons

@Composable
fun ToolkitNavigationBar(
    modifier: Modifier = Modifier,
    titles: List<String>,
    selectedIcons: List<ImageVector>,
    unselectedIcons: List<ImageVector>,
    onClickListener: (String) -> Unit,
    containerColor: Color = Color.White,
    selectedIconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    unselectedIconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    selectedTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    unselectedTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    indicatorColor: Color = MaterialTheme.colorScheme.primaryContainer

) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp,

        ) {
        titles.forEachIndexed { index, item ->
            ToolkitNavigationBarItem(
                icon = {
                    Icon(
                        tint = Color.Cyan,
                        imageVector = unselectedIcons[index],
                        contentDescription = item,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = selectedIcons[index],
                        contentDescription = item,
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClickListener(item)
                },
                selectedIconColor = selectedIconColor,
                unselectedIconColor = unselectedIconColor,
                selectedTextColor = selectedTextColor,
                unselectedTextColor = unselectedTextColor,
                indicatorColor = indicatorColor,
            )
        }
    }
}

@Composable
fun RowScope.ToolkitNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
    selectedIconColor: Color,
    unselectedIconColor: Color,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    indicatorColor: Color
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedIconColor,
            unselectedIconColor = unselectedIconColor,
            selectedTextColor = selectedTextColor,
            unselectedTextColor = unselectedTextColor,
            indicatorColor = indicatorColor,
        ),
    )
}

@Preview
@Composable
private fun Preview() {
    ToolkitNavigationBar(
        titles = listOf("For you", "Saved", "Interests"),
        selectedIcons = listOf(ToolkitIcons.Upcoming, ToolkitIcons.Bookmarks, ToolkitIcons.Grid3x3),
        unselectedIcons = listOf(ToolkitIcons.UpcomingBorder, ToolkitIcons.BookmarksBorder, ToolkitIcons.Grid3x3),
        onClickListener = {}
    )
}