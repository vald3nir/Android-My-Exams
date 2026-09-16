package com.vald3nir.myexams.presentation.features.exams.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
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
import com.vald3nir.myexams.presentation.components.PdfStepContent
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
internal fun CreateExamScreen(viewModel: CreateExamViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()
    val currentStep by viewModel.currentStepFlow.collectAsStateWithLifecycle()
    val openPdfLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { viewModel.readPDF(it) }

    when (uiState) {
        is BaseUiState.LoadingState -> ToolkitLoadingFullscreen()
        is BaseUiState.FinishState -> viewModel.navigateBack()
        else -> ScreenContent(
            uiModel = uiModel,
            currentStep = currentStep,
            onExamChanged = viewModel::onExamChanged,
            onNextStep = viewModel::goToNextStep,
            onPreviousStep = viewModel::goToPreviousStep,
            onSaveExam = viewModel::saveExam,
            onSelectPdf = { openPdfLauncher.launch(arrayOf("application/pdf")) },
        )
    }
}

@Composable
private fun ScreenContent(
    uiModel: CreateExamUiModel = CreateExamUiModel(),
    currentStep: CreateExamStep = CreateExamStep.Pdf,
    onExamChanged: (ExamDTO) -> Unit = {},
    onNextStep: () -> Unit = {},
    onPreviousStep: () -> Unit = {},
    onSaveExam: () -> Unit = {},
    onSelectPdf: () -> Unit = {},
) {
    ToolkitScaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.create_exam_screen_title),
                onBackPressed = onPreviousStep,
            )
        },
        bottomBar = {
            ToolkitFixedButton(
                label = stringResource(uiModel.bottomButtonRes(currentStep)),
                enabled = uiModel.isBottomButtonEnabled(currentStep),
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
                CreateExamStep.Pdf -> PdfStepContent(
                    title = stringResource(R.string.create_exam_step_pdf_title),
                    description = stringResource(R.string.create_exam_step_pdf_description),
                    onSelectPdf = onSelectPdf
                )

                CreateExamStep.Date -> DateStepContent(
                    title = stringResource(R.string.create_exam_step_date_title),
                    description = stringResource(R.string.create_exam_step_date_description),
                    selectedDate = uiModel.exam.date.orEmpty(),
                    onSelectDate = { onExamChanged(uiModel.exam.copy(date = it)) },
                )

                CreateExamStep.Lab -> LabStepContent(
                    title = stringResource(R.string.create_exam_step_lab_title),
                    description = stringResource(R.string.create_exam_step_lab_description),
                    labs = uiModel.labs,
                    topLabs = uiModel.topLabs,
                    labName = uiModel.exam.lab.orEmpty(),
                    onLabChanged = { onExamChanged(uiModel.exam.copy(lab = it)) },
                )

                CreateExamStep.Fields -> FieldsStepContent(
                    title = stringResource(R.string.create_exam_step_fields_title),
                    description = stringResource(R.string.create_exam_step_fields_description),
                    exam = uiModel.exam,
                    onExamChanged = onExamChanged,
                )
            }
        }
    }
}

@AppPreview
@Composable
internal fun Preview(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        ScreenContent(uiModel = contentData)
    }
}

@AppPreview
@Composable
internal fun PreviewDateStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        ScreenContent(
            uiModel = contentData,
            currentStep = CreateExamStep.Date,
        )
    }
}

@AppPreview
@Composable
internal fun PreviewLabStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        ScreenContent(
            uiModel = contentData,
            currentStep = CreateExamStep.Lab,
        )
    }
}

@AppPreview
@Composable
internal fun PreviewFieldsStep(@PreviewParameter(CreateExamProvider::class) contentData: CreateExamUiModel) {
    ToolkitPreviewContainer {
        ScreenContent(
            uiModel = contentData,
            currentStep = CreateExamStep.Fields,
        )
    }
}