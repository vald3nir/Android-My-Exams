package com.vald3nir.myexams.presentation.features.evolution

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.myexams.presentation.components.AppHeader
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.charts.ToolkitBarChart
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun EvolutionHistoryScreen(viewModel: EvolutionHistoryViewModel = hiltViewModel()) {
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()
    EvolutionHistoryScreenContent(charts = uiModel)
}

@Composable
private fun EvolutionHistoryScreenContent(charts: List<EvolutionFieldChartDTO>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(ToolkitSpacingMd),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AppHeader(
                title = stringResource(R.string.evolution_screen_title),
                description = stringResource(R.string.evolution_screen_description)
            )
        }
        items(charts) { chart ->
            FieldChartContent(chart = chart)
        }
    }
}

@Composable
private fun FieldChartContent(chart: EvolutionFieldChartDTO) {
    if (chart.points.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ToolkitSpacingMd),
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
        ) {
            ToolkitText(
                text = stringResource(chart.titleRes),
                style = ToolkitTextStyle.TitleMedium,
            )
            ToolkitText(
                text = stringResource(R.string.evolution_screen_no_data),
                style = ToolkitTextStyle.BodyMedium,
            )
        }
        return
    }
    ToolkitBarChart(
        title = stringResource(chart.titleRes),
        subtitle = chart.description,
        data = chart.points,
    )
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(EvolutionHistoryScreenProvider::class) charts: List<EvolutionFieldChartDTO>) {
    ToolkitPreviewContainer {
        EvolutionHistoryScreenContent(charts = charts)
    }
}