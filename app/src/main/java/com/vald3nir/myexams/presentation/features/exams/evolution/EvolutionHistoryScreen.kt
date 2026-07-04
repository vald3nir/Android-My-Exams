package com.vald3nir.myexams.presentation.features.exams.evolution

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.charts.ToolkitLineChart
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn

@Composable
internal fun EvolutionHistoryScreen(viewModel: EvolutionHistoryViewModel = hiltViewModel()) {
    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()
    EvolutionHistoryScreenContent(charts = screenData)
}

@Composable
private fun EvolutionHistoryScreenContent(charts: List<EvolutionFieldChartDTO>) {
    ToolkitColumn {
        AppTopBar(title = stringResource(R.string.evolution_screen_title))
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = stringResource(R.string.evolution_screen_description),
            style = ToolkitTextStyle.BodyMedium,
        )
        ToolkitSpaceHeight()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
        ) {
            items(charts) { chart ->
                FieldChartContent(chart = chart)
            }
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
    ToolkitLineChart(
        title = stringResource(chart.titleRes),
        data = chart.points,
        upperLimit = chart.upperLimit,
        upperLimitColor = chart.limitColor,
        lowerLimit = chart.lowerLimit,
        lowerLimitColor = chart.limitColor,
    )
}

@AppPreview
@Composable
private fun Preview(@PreviewParameter(EvolutionHistoryScreenProvider::class) charts: List<EvolutionFieldChartDTO>) {
    ToolkitPreviewContainer {
        EvolutionHistoryScreenContent(charts = charts)
    }
}