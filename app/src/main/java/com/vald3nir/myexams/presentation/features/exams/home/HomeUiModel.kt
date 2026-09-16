package com.vald3nir.myexams.presentation.features.exams.home

internal data class HomeUiModel(
    val items: List<ItemHomeUiModel>,
    val hasInternetConnection: Boolean,
)

internal data class ItemHomeUiModel(
    val idExam: String? = null,
    val date: String? = null,
    val lab: String? = null,
    val alerts: Int = 0
)