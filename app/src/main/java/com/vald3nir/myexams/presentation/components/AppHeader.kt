package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitAppHeader

@Composable
internal fun AppHeader() {
    ToolkitAppHeader(
        modifier = Modifier.padding(top = ToolkitSpacingXl),
        title = stringResource(R.string.app_name),
        description = stringResource(R.string.app_description),
        appLogo = R.drawable.ic_logo
    )
}

@Composable
internal fun AppHeader(title: String, description: String) {
    ToolkitAppHeader(
        modifier = Modifier.padding(top = ToolkitSpacingXl),
        title = title,
        description = description,
        appLogo = R.drawable.ic_logo
    )
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        AppHeader()
    }
}