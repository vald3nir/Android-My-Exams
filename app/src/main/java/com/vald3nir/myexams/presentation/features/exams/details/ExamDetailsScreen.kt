package com.vald3nir.myexams.presentation.features.exams.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamDetailsScreenDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.myexams.presentation.components.ExamFieldDetails
import com.vald3nir.myexams.presentation.components.ExamHeaderDetails
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitFixedButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
internal fun ExamDetailsScreen(
    examId: String,
    viewModel: ExamDetailsViewModel = hiltViewModel(),
    onClickEditExam: (id: String?) -> Unit,
    onNavigateBack: () -> Unit = viewModel::navigateBack,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()

    LaunchedEffect(examId) {
        viewModel.loadExam(examId)
    }

    when (uiState) {
        is BaseUiState.LoadingState -> ToolkitLoadingFullscreen()
        else -> ScreenContent(
            screenData = screenData,
            onClickEditExam = onClickEditExam,
            onNavigateBack = onNavigateBack,
        )
    }
}

@Composable
private fun ScreenContent(
    screenData: ExamDetailsScreenDTO,
    onClickEditExam: (id: String?) -> Unit,
    onNavigateBack: () -> Unit,
) {
    ToolkitScaffold(
        topBar = { AppTopBar(title = stringResource(R.string.exam_details_title), onBackPressed = onNavigateBack) },
        bottomBar = { ToolkitFixedButton(label = stringResource(R.string.exam_details_edit_button), onClick = { onClickEditExam(screenData.exam.id) }) },
    ) {
        ToolkitColumn(modifier = Modifier.padding(ToolkitSpacingMd), verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)) {
            ToolkitText(text = stringResource(R.string.exam_details_description), style = ToolkitTextStyle.BodyMedium)
            ExamHeaderDetails(exam = screenData.exam)
            ToolkitText(text = stringResource(R.string.exam_details_results_title), style = ToolkitTextStyle.TitleMedium)
            screenData.fields.forEach { field ->
                if (field.value != null) {
                    ExamFieldDetails(label = stringResource(field.label), value = field.value, warning = field.warning)
                }
            }
        }
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ScreenContent(
            screenData = ExamDetailsScreenDTO(
                exam = ExamDTO(
                    date = "05/08/2026",
                    lab = "Laboratorio Central",
                    totalCholesterol = 220,
                    hdl = 36,
                    notHdl = 184,
                    ldl = 145,
                    triglycerides = 180,
                    uricAcid = 7.5,
                ),
                validation = ExamValidatedDTO(
                    alertsSize = 4,
                    totalCholesterolMessage = "O valor apropriado e ser menor ou igual a 190",
                    hdlMessage = "O valor apropriado e ser maior ou igual a 40",
                    ldlMessage = "O valor apropriado e ser menor ou igual a 130",
                    uricAcidMessage = "O valor apropriado e ser entre 3.5 e 7.2",
                )
            ),
            onClickEditExam = {},
            onNavigateBack = {},
        )
    }
}