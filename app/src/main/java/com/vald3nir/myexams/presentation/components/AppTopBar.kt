package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.topbars.ToolkitTopBar

@Composable
fun AppTopBar(
    title: String,
    onBackPressed: (() -> Unit)? = null,
    extraIcon: ImageVector? = null,
    onClickExtraIcon: () -> Unit = {},
) = ToolkitTopBar(
    title = title,
    textCenterAligned = true,
    leftIcon = if (onBackPressed != null) ToolkitIconCatalog.ArrowBack else null,
    onClickLeftIcon = { onBackPressed?.invoke() },
    rightIcon = extraIcon,
    onClickRightIcon = onClickExtraIcon,
)