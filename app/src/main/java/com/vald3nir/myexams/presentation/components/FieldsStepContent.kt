package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun FieldsStepContent(
    title: String,
    description: String,
    exam: ExamDTO,
    onExamChanged: (ExamDTO) -> Unit
) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight()
        FieldSection(
            title = stringResource(R.string.total_cholesterol),
            description = stringResource(R.string.create_exam_total_cholesterol_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.total_cholesterol),
                inputValue = exam.totalCholesterol?.toInt(),
                onValueChange = { onExamChanged(exam.copy(totalCholesterol = it.toDouble())) },
            )
        }
        FieldSection(
            title = stringResource(R.string.hdl_d),
            description = stringResource(R.string.create_exam_hdl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.hdl_d),
                inputValue = exam.hdl?.toInt(),
                onValueChange = { onExamChanged(exam.copy(hdl = it.toDouble())) },
            )
        }
        FieldSection(
            title = stringResource(R.string.no_hdl),
            description = stringResource(R.string.create_exam_non_hdl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.no_hdl),
                inputValue = exam.notHdl?.toInt(),
                onValueChange = { onExamChanged(exam.copy(notHdl = it.toDouble())) },
            )
        }
        FieldSection(
            title = stringResource(R.string.ldl),
            description = stringResource(R.string.create_exam_ldl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.ldl),
                inputValue = exam.ldl?.toInt(),
                onValueChange = { onExamChanged(exam.copy(ldl = it.toDouble())) },
            )
        }
        FieldSection(
            title = stringResource(R.string.triglycerides),
            description = stringResource(R.string.create_exam_triglycerides_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.triglycerides),
                inputValue = exam.triglycerides?.toInt(),
                onValueChange = { onExamChanged(exam.copy(triglycerides = it.toDouble())) },
            )
        }
    }
}

@Composable
private fun FieldSection(title: String, description: String, content: @Composable () -> Unit) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight(ToolkitSpacingMd)
        content()
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        FieldsStepContent(
            title = stringResource(R.string.create_exam_step_fields_title),
            description = stringResource(R.string.create_exam_step_fields_description),
            exam = ExamDTO(),
            onExamChanged = {},
        )
    }
}