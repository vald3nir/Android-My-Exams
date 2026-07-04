package com.vald3nir.myexams.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class LaboratoryDTO(
    val id: String? = null,
    val name: String? = null,
)