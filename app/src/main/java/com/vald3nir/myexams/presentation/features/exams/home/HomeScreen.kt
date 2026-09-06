package com.vald3nir.myexams.presentation.features.exams.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.openWifiSettings
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.inputs.ToolkitSearchFiled
import com.vald3nir.toolkit.designsystem.components.lists.ToolkitFieldCard
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickCreateExam: () -> Unit = {},
    onClickOpenExam: (id: String?) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val homeData by viewModel.screenDataFlow.collectAsStateWithLifecycle()

    when (uiState) {
        is BaseUiState.LoadingState -> {
            ToolkitLoadingFullscreen()
            return
        }

        is BaseUiState.EmptySate -> {
            EmptyState(onClickCreateExam = onClickCreateExam)
            return
        }

        is BaseUiState.OffLineState -> {
            OfflineState()
            return
        }

        else -> {
            ScreenContent(
                searchQuery = searchQuery,
                items = homeData?.items.orEmpty(),
                onClickCreateExam = onClickCreateExam,
                filterLists = viewModel::onSearchQueryChanged,
                onClickOpenExam = onClickOpenExam
            )
        }
    }
}

@Composable
private fun ScreenContent(
    searchQuery: String = "",
    items: List<ItemHomeUIModel>,
    filterLists: (key: String) -> Unit = {},
    onClickCreateExam: () -> Unit = {},
    onClickOpenExam: (id: String?) -> Unit = {}
) {
    HomeStateContainer {
        ToolkitColumn {

            ToolkitSearchFiled(
                label = stringResource(R.string.home_screen_search_list),
                searchQuery = searchQuery,
                onValueChange = filterLists,
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(ToolkitSpacingMd),
                verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(items) { item ->
                    ToolkitFieldCard(
                        label = item.lab ?: stringResource(R.string.home_screen_laboratory_not_specified),
                        value = item.date.orEmpty(),
                        onEdit = { onClickOpenExam(item.idExam) },
                        imageVector = ToolkitIconCatalog.ArrowIndicatorRight
                    )
                }
            }

            ToolkitBaseButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ToolkitSpacingMd),
                text = stringResource(R.string.home_screen_btn_add),
                leadingIcon = ToolkitIconCatalog.Add,
                onClick = onClickCreateExam
            )
        }
    }
}


@Composable
private fun EmptyState(onClickCreateExam: () -> Unit = {}) {
    HomeStateContainer {
        ToolkitEmptyStateScreen(
            title = stringResource(R.string.empty_state_message),
            btnText = stringResource(R.string.empty_state_btn_label),
            imageVector = ToolkitIconCatalog.Inbox,
            onClickBtn = onClickCreateExam,
        )
    }
}

@Composable
private fun OfflineState() {
    val context = LocalContext.current
    HomeStateContainer {
        ToolkitEmptyStateScreen(
            title = stringResource(R.string.offline_state_message),
            btnText = stringResource(R.string.offline_state_btn_label),
            imageVector = ToolkitIconCatalog.WifiOff,
            leadingIcon = ToolkitIconCatalog.Settings,
            onClickBtn = { context.openWifiSettings() },
        )
    }
}

@Composable
private fun HomeStateContainer(content: @Composable () -> Unit) {
    ToolkitColumn {
        AppTopBar(title = stringResource(R.string.home_screen_title))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}


@AppPreview
@Composable
private fun PreviewContent(@PreviewParameter(HomeProvider::class) exams: List<ItemHomeUIModel>) {
    ToolkitPreviewContainer {
        ScreenContent(items = exams)
    }
}

@AppPreview
@Composable
private fun PreviewEmptyState() {
    ToolkitPreviewContainer {
        EmptyState()
    }
}

@AppPreview
@Composable
private fun PreviewOfflineState() {
    ToolkitPreviewContainer {
        OfflineState()
    }
}