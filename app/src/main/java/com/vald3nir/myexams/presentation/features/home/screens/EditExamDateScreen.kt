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
import com.vald3nir.myexams.presentation.components.ComponentSelectExamDate
import com.vald3nir.myexams.presentation.features.home.ExamsHomeScope
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import kotlinx.coroutines.launch

@Composable
internal fun ExamsHomeScope.EditExamDateScreen(examID: Long?) {
    val context = LocalContext.current
    val colors = context.loadAppColorSchema()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val currentExam: ExamDTO? by viewModel.currentExam.collectAsState()
    var selectedDate by remember { mutableStateOf(currentExam?.date.orEmpty()) }

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
                    title = stringResource(R.string.select_exam_date),
                    colors = colors,
                    onBackClick = { onBackPressed() },
                )
            },
            content = {
                ComponentSelectExamDate(
                    selectedDate = selectedDate,
                    onSelectDate = { selectedDate = it },
                    colors = colors
                )
            },
            bottomBar = {
                ToolkitFixedButton(
                    colors = colors,
                    enabled = selectedDate.isNotEmpty(),
                    label = stringResource(R.string.update),
                    onClick = { viewModel.updateExam(currentExam?.copy(date = selectedDate)) }
                )
            }
        )
    }
}