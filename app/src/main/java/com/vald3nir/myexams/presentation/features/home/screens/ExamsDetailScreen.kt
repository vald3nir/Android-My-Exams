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
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.presentation.components.ComponentEditExamMenu
import com.vald3nir.myexams.presentation.components.ComponentExamDate
import com.vald3nir.myexams.presentation.components.ComponentExamFieldsViewer
import com.vald3nir.myexams.presentation.components.ComponentExamLab
import com.vald3nir.myexams.presentation.features.ScreenEvents
import com.vald3nir.myexams.presentation.features.home.ExamsHomeScope
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitColumn
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import kotlinx.coroutines.launch


@Composable
internal fun ExamsHomeScope.HomeDetailScreen(examID: Long?) {
    NavigationObserver()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val colors = context.loadAppColorSchema()
    val currentExam: ExamDTO? by viewModel.currentExam.collectAsState()
    val profile: ProfileDTO? by viewModel.currentProfile.collectAsState()
    var showMenu: Boolean by remember { mutableStateOf(false) }

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onLoading = { isLoading = it },
        onCallbackScreen = { event ->
            if (event == ScreenEvents.ExamDeleted) {
                onBackPressed(message = context.getString(R.string.message_exam_deleted))
            }
        }
    )

    viewModel.loadExam(examID)
    viewModel.loadProfile()
    snackBarHostState.ShowBackPressedMessage()

    AppTheme {
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            ToolkitBaseContainer(
                backgroundColor = colors.backgroundColor,
                snackBarHostState = snackBarHostState,
                topBarContent = {
                    ToolkitGenericToolbarComponent(
                        title = stringResource(R.string.exam_detail),
                        colors = colors,
                        onBackClick = { onBackPressed() },
                        genericContent = {
                            ToolkitIcon(
                                imageVector = ToolkitIcons.MoreVert,
                                tint = colors.toolbarIconTint,
                                onClick = {
                                    showMenu = true
                                }
                            )
                        }
                    )
                },
                content = {
                    ToolkitColumn(flagNoPadding = true) {
                        currentExam?.let { exam ->
                            ComponentExamLab(exam = exam, colors = colors, onClick = { redirectToEditExamLab(examID) })
                            ComponentExamDate(exam = exam, colors = colors, onClick = { redirectToEditExamDate(examID) })
                            DefaultSpaceHeight()
                            ComponentExamFieldsViewer(exam = exam, profile = profile, colors = colors, onClick = { redirectToEditExam(examID) })
                            DefaultSpaceHeight()
                        }
                        if (showMenu) {
                            ComponentEditExamMenu(
                                colors = colors,
                                onEditExamLab = { redirectToEditExamLab(examID) },
                                onEditExamDate = { redirectToEditExamDate(examID) },
                                onEditExamFields = { redirectToEditExam(examID) },
                                onDeleteExam = { deleteExam(examID) },
                                onDismissRequest = { showMenu = false },
                            )
                        }
                    }
                }
            )
        }
    }
}