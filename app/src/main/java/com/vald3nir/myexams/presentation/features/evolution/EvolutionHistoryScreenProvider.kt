package com.vald3nir.myexams.presentation.features.evolution

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO

internal class EvolutionHistoryScreenProvider : PreviewParameterProvider<List<EvolutionFieldChartDTO>> {

    override val values: Sequence<List<EvolutionFieldChartDTO>> = sequenceOf(

        listOf(
            EvolutionFieldChartDTO(
                titleRes = R.string.total_cholesterol,
                description = "O valor apropriado é ser menor ou igual a 150",
                points = listOf(
                    ItemChartDTO(180f, "01/01"),
                    ItemChartDTO(210f, "01/03"),
                    ItemChartDTO(195f, "01/06"),
                    ItemChartDTO(230f, "01/09"),
                ),
            ),
            EvolutionFieldChartDTO(
                titleRes = R.string.hdl_d,
                description = "O valor apropriado é ser menor ou igual a 120",
                points = listOf(),
            )
        )
    )
}