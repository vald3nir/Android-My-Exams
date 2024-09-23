package com.vald3nir.my_exams.domain.validators

data class ValidatorStatus(
    val passed: Boolean = true,
    val description: String? = null
)