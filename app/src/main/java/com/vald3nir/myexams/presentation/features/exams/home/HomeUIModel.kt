package com.vald3nir.myexams.presentation.features.exams.home

import com.vald3nir.myexams.domain.dto.ExamDTO

internal data class HomeUIModel(
    val items: List<ItemHomeUIModel>,
    val hasInternetConnection: Boolean,
)

internal data class ItemHomeUIModel(
    val idExam: String? = null,
    val date: String? = null,
    val lab: String? = null,
)

internal fun bindHomeUIModel(exams: List<ExamDTO>, hasInternetConnection: Boolean, filterText: String): HomeUIModel {
    val normalizedQuery = filterText.trim().lowercase()
    var items: List<ItemHomeUIModel> = exams.map { exam ->
        ItemHomeUIModel(
            idExam = exam.id,
            date = exam.date,
            lab = exam.lab
        )
    }
    if (normalizedQuery.isNotEmpty()) {
        items = items.filter { it.filter(normalizedQuery) }
    }
    return HomeUIModel(
        items = items,
        hasInternetConnection = hasInternetConnection,
    )
}

private fun ItemHomeUIModel.filter(query: String) = date?.contains(query, ignoreCase = true) == true || lab?.contains(query, ignoreCase = true) == true