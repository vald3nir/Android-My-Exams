package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun DateStepContent(
    title: String,
    description: String,
    selectedDate: String,
    onSelectDate: (String) -> Unit,
) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight()
        SelectExamDate(
            selectedDate = selectedDate,
            onSelectDate = onSelectDate,
        )
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 250.dp)) {
        DateStepContent(
            title = stringResource(R.string.create_exam_step_date_title),
            description = stringResource(R.string.create_exam_step_date_description),
            selectedDate = "2024-06-01",
            onSelectDate = {},
        )
    }
}