package com.vald3nir.myexams.presentation.features.exams.evolution

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO

internal class EvolutionHistoryScreenProvider : PreviewParameterProvider<List<EvolutionFieldChartDTO>> {

    override val values: Sequence<List<EvolutionFieldChartDTO>> = sequenceOf(

        listOf(
            EvolutionFieldChartDTO(
                titleRes = R.string.total_cholesterol,
                points = listOf(
                    ItemChartDTO(180f, "01/01"),
                    ItemChartDTO(210f, "01/03"),
                    ItemChartDTO(195f, "01/06"),
                    ItemChartDTO(230f, "01/09"),
                ),
                upperLimit = 190f,
            ),
            EvolutionFieldChartDTO(
                titleRes = R.string.hdl_d,
                points = listOf(
                    ItemChartDTO(38f, "01/01"),
                    ItemChartDTO(42f, "01/03"),
                    ItemChartDTO(55f, "01/06"),
                    ItemChartDTO(48f, "01/09"),
                ),
                lowerLimit = 40f,
            ),
            EvolutionFieldChartDTO(
                titleRes = R.string.uric_acid,
                points = listOf(
                    ItemChartDTO(2.4f, "01/01"),
                    ItemChartDTO(5.1f, "01/03"),
                    ItemChartDTO(7.9f, "01/06"),
                ),
                lowerLimit = 3.5f,
                upperLimit = 7.2f,
            ),
        )


    )

}