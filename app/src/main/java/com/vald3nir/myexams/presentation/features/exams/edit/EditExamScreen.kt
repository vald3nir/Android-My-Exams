package com.vald3nir.myexams.presentation.features.exams.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.enums.CreateExamStep
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.myexams.presentation.components.DateStepContent
import com.vald3nir.myexams.presentation.components.FieldsStepContent
import com.vald3nir.myexams.presentation.components.LabStepContent
import com.vald3nir.myexams.presentation.features.exams.create.CreateExamProvider
import com.vald3nir.myexams.presentation.features.exams.create.CreateExamUiModel
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
internal fun EditExamScreen(
    examId: String,
    viewModel: EditExamViewModel = hiltViewModel(),
) {
    LaunchedEffect(examId) {
        viewModel.loadExam(examId)
    }

    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()
    val currentStep by viewModel.currentStepFlow.collectAsStateWithLifecycle()

    EditExamScreenContent(
        contentData = screenData,
        currentStep = currentStep,
        onExamChanged = viewModel::onExamChanged,
        onNextStep = viewModel::goToNextStep,
        onPreviousStep = viewModel::goToPreviousStep,
        onSaveExam = viewModel::saveExam,
    )
}

@Composable
private fun EditExamScreenContent(
    contentData: CreateExamUiModel = CreateExamUiModel(),
    currentStep: CreateExamStep = CreateExamStep.Date,
    onExamChanged: (ExamDTO) -> Unit = {},
    onNextStep: () -> Unit = {},
    onPreviousStep: () -> Unit = {},
    onSaveExam: () -> Unit = {},
) {
    ToolkitScaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.update_exam),
                onBackPressed = onPreviousStep,
            )
        },
        bottomBar = {
            ToolkitFixedButton(
                label = stringResource(if (currentStep == CreateExamStep.Fields) R.string.btn_update else R.string.btn_continue),
                enabled = contentData.isBottomButtonEnabled(currentStep),
                onClick = {
                    if (currentStep == CreateExamStep.Fields) {
                        onSaveExam()
                    } else {
                        onNextStep()
                    }
                },
            )
        },
    ) {
        ToolkitColumn(verticalArrangement = Arrangement.spacedBy(ToolkitSpacingLg)) {
            when (currentStep) {
                CreateExamStep.Pdf,
                CreateExamStep.Date -> DateStepContent(
                    title = stringResource(R.string.edit_exam_step_date_title),
                    description = stringResource(R.string.edit_exam_step_date_description),
                    selectedDate = contentData.exam.date.orEmpty(),
                    onSelectDate = { onExamChanged(contentData.exam.copy(date = it)) },
                )

                CreateExamStep.Lab -> LabStepContent(
                    title = stringResource(R.string.edit_exam_step_lab_title),
                    description = stringResource(R.string.edit_exam_step_lab_description),
                    labs = contentData.labs,
                    topLabs = contentData.topLabs,
                    labName = contentData.exam.lab.orEmpty(),
                    onLabChanged = { onExamChanged(contentData.exam.copy(lab = it)) },
                )

                CreateExamStep.Fields -> FieldsStepContent(
                    title = stringResource(R.string.edit_exam_step_fields_title),
                    description = stringResource(R.string.edit_exam_step_fields_description),
                    exam = contentData.exam,
                    onExamChanged = onExamChanged,
                )
            }
        }
    }
}


@AppPreview
@Composable
private fun Preview(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        EditExamScreenContent(contentData = contentData)
    }
}

@AppPreview
@Composable
private fun PreviewDateStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        EditExamScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Date,
        )
    }
}

@AppPreview
@Composable
private fun PreviewLabStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        EditExamScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Lab,
        )
    }
}

@AppPreview
@Composable
private fun PreviewFieldsStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        EditExamScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Fields,
        )
    }
}
