package com.vald3nir.myexams.presentation.features.insertExam.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.mock.examMock1
import com.vald3nir.myexams.presentation.components.ComponentExamFieldsInput
import com.vald3nir.myexams.presentation.features.insertExam.InsertExamScope
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import kotlinx.coroutines.launch

@Composable
internal fun InsertExamScope.NewExamScreen() {
    NavigationObserver()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onLoading = { isLoading = it },
        onCallbackScreen = { redirectToHome() }
    )

    AppTheme {
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            NewExamScreenContent(
                newExam = newExam,
                colors = context.loadAppColorSchema(),
                snackBarHostState = snackBarHostState,
                onBackPressed = { onBackPressed() },
                onFillExam = { insertNewExam(it) }
            )
        }
    }
}


@Composable
private fun NewExamScreenContent(
    newExam: ExamDTO,
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onBackPressed: () -> Unit = {},
    onFillExam: (ExamDTO?) -> Unit = {},
) {
    var examFilled: ExamDTO? by remember { mutableStateOf(null) }
    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = stringResource(R.string.insert_new_exam),
                colors = colors,
                onBackClick = onBackPressed
            )
        },
        content = {
            ComponentExamFieldsInput(
                currentExam = newExam,
                colors = colors,
                onUpdateExam = { examFilled = it },
            )
        },
        bottomBar = {
            ToolkitFixedButton(
                colors = colors,
                enabled = examFilled != null,
                label = stringResource(R.string.insert),
                onClick = { onFillExam(examFilled) }
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    NewExamScreenContent(
        newExam = examMock1,
        colors = appMyExamsTheme()
    )
}