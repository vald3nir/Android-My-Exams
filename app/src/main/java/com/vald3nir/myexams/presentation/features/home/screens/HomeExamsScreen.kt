package com.vald3nir.myexams.presentation.features.home.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.mock.examMock1
import com.vald3nir.myexams.domain.mock.examMock2
import com.vald3nir.myexams.domain.mock.examMock3
import com.vald3nir.myexams.domain.mock.profileMock
import com.vald3nir.myexams.presentation.components.ComponentExamResume
import com.vald3nir.myexams.presentation.features.home.ExamsHomeScope
import com.vald3nir.myexams.presentation.features.profile.redirectEditProfileActivity
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFloatingButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitSearchFieldComponent
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitToolbarWithAvatarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import com.vald3nir.toolkit.compose.templates.ToolkitEmptyStateScreen
import kotlinx.coroutines.launch


@Composable
internal fun ExamsHomeScope.HomeExamsScreen() {
    NavigationObserver()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val shoppingLists by viewModel.filteredList.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val profile: ProfileDTO? by viewModel.currentProfile.collectAsState()

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onLoading = { isLoading = it },
    )

    snackBarHostState.ShowBackPressedMessage()
    viewModel.loadProfile()

    AppTheme {
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            ExamsHomeScreenContent(
                exams = shoppingLists,
                profile = profile,
                userImageUrl = userPhotoUrl(),
                colors = context.loadAppColorSchema(),
                searchQuery = searchQuery,
                onQueryChange = updateSearchQueryEvent,
                onClickSeeExam = { redirectToExamDetail(it) },
                onClickAddNewExam = { redirectToCreateNewExam(context) },
                snackBarHostState = snackBarHostState,
                onClickProfile = { context.redirectEditProfileActivity() }
            )
        }
    }
}


@Composable
private fun ExamsHomeScreenContent(
    exams: List<ExamDTO>,
    profile: ProfileDTO?,
    userImageUrl: String? = null,
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    searchQuery: String = "",
    onQueryChange: (String) -> Unit = {},
    onClickSeeExam: (Long?) -> Unit = {},
    onClickAddNewExam: () -> Unit = {},
    onClickProfile: () -> Unit = {},
) {
    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitToolbarWithAvatarComponent(
                title = stringResource(R.string.app_name),
                userImageUrl = userImageUrl,
                colors = colors,
                onAvatarClick = onClickProfile,
            )
        },
        floatingActionButton = {
            if (exams.isNotEmpty()) {
                ToolkitFloatingButton(
                    colors = colors,
                    onClick = onClickAddNewExam
                )
            }
        },
        content = {
            ToolkitSearchFieldComponent(
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
                useUnderline = true,
                placeholder = stringResource(R.string.research),
                colors = colors,
            )
            if (exams.isEmpty()) {
                ToolkitEmptyStateScreen(
                    imageVector = ToolkitIcons.Inbox,
                    colors = colors,
                    onAddClick = onClickAddNewExam,
                )
            } else {
                DefaultSpaceHeight()
                LazyColumn {
                    itemsIndexed(exams) { _, item ->
                        ComponentExamResume(
                            exam = item,
                            colors = colors,
                            profile = profile,
                            onClickSeeExam = onClickSeeExam
                        )
                        DefaultSpaceHeight()
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ExamsHomeScreenContent(
        exams = listOf(examMock1, examMock2, examMock3),
        profile = profileMock,
        colors = appMyExamsTheme()
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyState() {
    ExamsHomeScreenContent(
        exams = emptyList(),
        profile = profileMock,
        colors = appMyExamsTheme()
    )
}