package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun StepHeader(
    title: String,
    description: String,
) {
    Column(modifier = Modifier.padding(horizontal = ToolkitSpacingMd)) {
        ToolkitText(text = title, style = ToolkitTextStyle.TitleMedium)
        ToolkitSpaceHeight()
        ToolkitText(text = description, style = ToolkitTextStyle.BodyMedium)
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 250.dp)) {
        StepHeader(
            title = "Step Title",
            description = "Step Description"
        )
    }
}