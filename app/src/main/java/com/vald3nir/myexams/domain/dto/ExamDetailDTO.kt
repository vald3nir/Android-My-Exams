package com.vald3nir.myexams.domain.dto

import androidx.annotation.StringRes

internal data class ExamDetailDTO(
    @StringRes val label: Int,
    val value: String?,
    val warning: String?,
)