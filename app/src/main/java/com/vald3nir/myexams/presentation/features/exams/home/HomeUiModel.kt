package com.vald3nir.myexams.presentation.features.exams.home

import com.vald3nir.myexams.domain.dto.ExamDTO

internal data class HomeUiModel(
    val items: List<ItemHomeUiModel>,
    val hasInternetConnection: Boolean,
)

internal data class ItemHomeUiModel(
    val idExam: String? = null,
    val date: String? = null,
    val lab: String? = null,
)

internal fun bindHomeUIModel(exams: List<ExamDTO>, hasInternetConnection: Boolean, filterText: String): HomeUiModel {
    val normalizedQuery = filterText.trim().lowercase()
    var items: List<ItemHomeUiModel> = exams.map { exam ->
        ItemHomeUiModel(
            idExam = exam.id,
            date = exam.date,
            lab = exam.lab
        )
    }
    if (normalizedQuery.isNotEmpty()) {
        items = items.filter { it.filter(normalizedQuery) }
    }
    return HomeUiModel(
        items = items,
        hasInternetConnection = hasInternetConnection,
    )
}

private fun ItemHomeUiModel.filter(query: String) = date?.contains(query, ignoreCase = true) == true || lab?.contains(query, ignoreCase = true) == true