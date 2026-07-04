package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun ExamFieldDetails(label: String, value: String, warning: String? = null) {
    val textStyle = ToolkitTextStyle.TitleSmall
    ToolkitCard(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Column(
                modifier = Modifier.padding(ToolkitSpacingMd),
                verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ToolkitText(text = label, style = textStyle)
                    ToolkitText(text = value, style = textStyle)
                }
                warning?.let {
                    ToolkitText(
                        text = warning,
                        style = textStyle,
                        textColor = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    )
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ExamFieldDetails(label = "HDL-D", value = "50", warning = "Warning: Low HDL")
    }
}