package com.vald3nir.myexams.domain.dto

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO

internal data class EvolutionFieldChartDTO(
    @StringRes val titleRes: Int,
    val points: List<ItemChartDTO>,
    val upperLimit: Float? = null,
    val lowerLimit: Float? = null,
    val limitColor: Color = Color(0xFFFF0000)
)