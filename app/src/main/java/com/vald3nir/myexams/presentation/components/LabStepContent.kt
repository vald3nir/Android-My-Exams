package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSelectButtonGroup
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun LabStepContent(
    title: String,
    description: String,
    labs: List<String>,
    topLabs: List<String>,
    labName: String,
    onLabChanged: (String) -> Unit,
) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight()
        ToolkitAutoCompleteInput(
            inputValue = labName,
            suggestionList = labs,
            label = stringResource(R.string.create_exam_screen_select_lab_label),
            placeholder = stringResource(R.string.create_exam_screen_select_lab_placeholder),
            onValueChange = onLabChanged,
        )
        if (topLabs.isNotEmpty()) {
            ToolkitText(
                modifier = Modifier.padding(ToolkitSpacingMd),
                text = stringResource(R.string.create_exam_step_lab_optional_description),
                style = ToolkitTextStyle.TitleSmall
            )
            ToolkitSelectButtonGroup(
                modifier = Modifier.heightIn(max = 500.dp),
                items = topLabs,
                onItemSelected = onLabChanged
            )
        }
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 400.dp)) {
        LabStepContent(
            title = stringResource(R.string.create_exam_step_lab_title),
            description = stringResource(R.string.create_exam_step_lab_description),
            labs = listOf("Lab 1", "Lab 2", "Lab 3"),
            topLabs = listOf("Lab 1", "Lab 3"),
            labName = "Lab 1",
            onLabChanged = {},
        )
    }
}