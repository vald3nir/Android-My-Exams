package com.vald3nir.myexams.presentation.features.home.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.presentation.components.ComponentExamFieldsInput
import com.vald3nir.myexams.presentation.features.home.ExamsHomeScope
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import kotlinx.coroutines.launch

@Composable
internal fun ExamsHomeScope.EditExamScreen(examID: Long?) {
    val context = LocalContext.current
    val colors = context.loadAppColorSchema()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val currentExam: ExamDTO? by viewModel.currentExam.collectAsState()
    var examFilled: ExamDTO? by remember { mutableStateOf(currentExam) }

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onCallbackScreen = { event ->
            onBackPressed(message = context.getString(R.string.message_exam_updated))
        }
    )

    viewModel.loadExam(examID)

    AppTheme {
        ToolkitBaseContainer(
            backgroundColor = colors.backgroundColor,
            topBarContent = {
                ToolkitGenericToolbarComponent(
                    title = stringResource(R.string.update_exam),
                    colors = colors,
                    onBackClick = { onBackPressed() },
                )
            },
            content = {
                ComponentExamFieldsInput(
                    currentExam = examFilled,
                    colors = colors,
                    onUpdateExam = { examFilled = it }
                )
            },
            bottomBar = {
                ToolkitFixedButton(
                    colors = colors,
                    enabled = examFilled != null,
                    label = stringResource(R.string.update),
                    onClick = { viewModel.updateExam(examFilled) }
                )
            }
        )
    }
}