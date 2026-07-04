package com.vald3nir.myexams.domain.dto

internal data class HomeScreenDTO(
    val profile: ProfileDTO = ProfileDTO(),
    val exams: List<ExamsHomeScreenDTO> = emptyList(),
    val hasInternetConnection: Boolean = true,
)

internal data class ExamsHomeScreenDTO(
    val id: String? = null,
    val date: String? = null,
    val lab: String? = null,
    val hasAlerts: Boolean = false, //todo valdenir remover campo
) {
    fun filter(query: String) = date?.contains(query, ignoreCase = true) == true || lab?.contains(query, ignoreCase = true) == true
}