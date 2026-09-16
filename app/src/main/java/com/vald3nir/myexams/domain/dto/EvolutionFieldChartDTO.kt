package com.vald3nir.myexams.domain.dto

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO

internal data class EvolutionFieldChartDTO(
    @StringRes val titleRes: Int,
    val description: String,
    val points: List<ItemChartDTO>,
)