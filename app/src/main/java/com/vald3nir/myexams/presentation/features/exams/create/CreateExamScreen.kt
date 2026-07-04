package com.vald3nir.myexams.presentation.features.exams.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.CreateExamScreenDTO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.enums.CreateExamStep
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.myexams.presentation.components.SelectExamDate
import com.vald3nir.toolkit.core.utils.extensions.orZero
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitAutoCompleteInput
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputDecimal
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitInputInteger
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSelectButtonGroup
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
internal fun CreateExamScreen(
    viewModel: CreateExamViewModel = hiltViewModel(),
) {
    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()
    val currentStep by viewModel.currentStepFlow.collectAsStateWithLifecycle()
    val openPdfLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { viewModel.readPDF(it) }

    ScreenContent(
        contentData = screenData,
        currentStep = currentStep,
        onExamChanged = viewModel::onExamChanged,
        onNextStep = viewModel::goToNextStep,
        onPreviousStep = viewModel::goToPreviousStep,
        onSaveExam = viewModel::saveExam,
        onSelectPdf = { openPdfLauncher.launch(arrayOf("application/pdf")) },
    )
}

@Composable
private fun ScreenContent(
    contentData: CreateExamScreenDTO = CreateExamScreenDTO(),
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
                label = stringResource(contentData.bottomButtonRes(currentStep)),
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
                CreateExamStep.Pdf -> PdfStepContent(onSelectPdf = onSelectPdf)

                CreateExamStep.Date -> DateStepContent(
                    selectedDate = contentData.exam.date.orEmpty(),
                    onSelectDate = { onExamChanged(contentData.exam.copy(date = it)) },
                )

                CreateExamStep.Lab -> LabStepContent(
                    labs = contentData.labs,
                    topLabs = contentData.topLabs,
                    labName = contentData.exam.lab.orEmpty(),
                    onLabChanged = { onExamChanged(contentData.exam.copy(lab = it)) },
                )

                CreateExamStep.Fields -> FieldsStepContent(
                    contentData = contentData,
                    onExamChanged = onExamChanged,
                )
            }
        }
    }
}

@Composable
private fun PdfStepContent(onSelectPdf: () -> Unit) {
    Column {
        StepHeader(
            title = stringResource(R.string.create_exam_step_pdf_title),
            description = stringResource(R.string.create_exam_step_pdf_description),
        )
        ToolkitSpaceHeight()
        ToolkitOutlinedButton(
            modifier = Modifier
                .padding(horizontal = ToolkitSpacingMd)
                .fillMaxWidth(),
            onClick = onSelectPdf,
            text = stringResource(R.string.create_exam_step_pdf_select_button),
        )
        ToolkitSpaceHeight(ToolkitSpacingMd)
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = stringResource(R.string.create_exam_step_pdf_optional_description),
            style = ToolkitTextStyle.BodySmall,
        )
    }
}

@Composable
private fun DateStepContent(
    selectedDate: String,
    onSelectDate: (String) -> Unit,
) {
    Column {
        StepHeader(
            title = stringResource(R.string.create_exam_step_date_title),
            description = stringResource(R.string.create_exam_step_date_description),
        )
        ToolkitSpaceHeight()
        SelectExamDate(
            selectedDate = selectedDate,
            onSelectDate = onSelectDate,
        )
    }
}

@Composable
private fun LabStepContent(
    labs: List<String>,
    topLabs: List<String>,
    labName: String,
    onLabChanged: (String) -> Unit,
) {
    Column {
        StepHeader(
            title = stringResource(R.string.create_exam_step_lab_title),
            description = stringResource(R.string.create_exam_step_lab_description),
        )
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

@Composable
private fun FieldsStepContent(
    contentData: CreateExamScreenDTO,
    onExamChanged: (ExamDTO) -> Unit,
) {
    Column {
        StepHeader(
            title = stringResource(R.string.create_exam_step_fields_title),
            description = stringResource(R.string.create_exam_step_fields_description),
        )
        ToolkitSpaceHeight()
        FieldSection(
            title = stringResource(R.string.total_cholesterol),
            description = stringResource(R.string.create_exam_total_cholesterol_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.total_cholesterol),
                inputValue = contentData.exam.totalCholesterol,
                onValueChange = { onExamChanged(contentData.exam.copy(totalCholesterol = it)) },
            )
        }
        FieldSection(
            title = stringResource(R.string.hdl_d),
            description = stringResource(R.string.create_exam_hdl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.hdl_d),
                inputValue = contentData.exam.hdl,
                onValueChange = { onExamChanged(contentData.exam.copy(hdl = it)) },
            )
        }
        FieldSection(
            title = stringResource(R.string.no_hdl),
            description = stringResource(R.string.create_exam_non_hdl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.no_hdl),
                inputValue = contentData.exam.notHdl,
                onValueChange = { onExamChanged(contentData.exam.copy(notHdl = it)) },
            )
        }
        FieldSection(
            title = stringResource(R.string.ldl),
            description = stringResource(R.string.create_exam_ldl_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.ldl),
                inputValue = contentData.exam.ldl,
                onValueChange = { onExamChanged(contentData.exam.copy(ldl = it)) },
            )
        }
        FieldSection(
            title = stringResource(R.string.triglycerides),
            description = stringResource(R.string.create_exam_triglycerides_description),
        ) {
            ToolkitInputInteger(
                label = stringResource(R.string.triglycerides),
                inputValue = contentData.exam.triglycerides,
                onValueChange = { onExamChanged(contentData.exam.copy(triglycerides = it)) },
            )
        }
        FieldSection(
            title = stringResource(R.string.uric_acid),
            description = stringResource(R.string.create_exam_uric_acid_description),
        ) {
            ToolkitInputDecimal(
                label = stringResource(R.string.uric_acid),
                inputValue = contentData.exam.uricAcid.orZero(),
                onValueChange = {
                    onExamChanged(contentData.exam.copy(uricAcid = if (it <= 0) null else it))
                },
            )
        }
    }
}

@Composable
private fun StepHeader(
    title: String,
    description: String,
) {
    Column(modifier = Modifier.padding(horizontal = ToolkitSpacingMd)) {
        ToolkitText(text = title, style = ToolkitTextStyle.TitleMedium)
        ToolkitSpaceHeight()
        ToolkitText(text = description, style = ToolkitTextStyle.BodyMedium)
    }
}

@Composable
private fun FieldSection(
    title: String,
    description: String,
    content: @Composable () -> Unit,
) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight(ToolkitSpacingMd)
        content()
    }
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(CreateExamScreenProvider::class) contentData: CreateExamScreenDTO) {
    ToolkitPreviewContainer {
        ScreenContent(contentData = contentData)
    }
}

@AppPreview
@Composable
private fun PreviewDateStep(@PreviewParameter(CreateExamScreenProvider::class) contentData: CreateExamScreenDTO) {
    ToolkitPreviewContainer {
        ScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Date,
        )
    }
}

@AppPreview
@Composable
private fun PreviewLabStep(@PreviewParameter(CreateExamScreenProvider::class) contentData: CreateExamScreenDTO) {
    ToolkitPreviewContainer {
        ScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Lab,
        )
    }
}

@AppPreview
@Composable
private fun PreviewFieldsStep(@PreviewParameter(CreateExamScreenProvider::class) contentData: CreateExamScreenDTO) {
    ToolkitPreviewContainer {
        ScreenContent(
            contentData = contentData,
            currentStep = CreateExamStep.Fields,
        )
    }
}