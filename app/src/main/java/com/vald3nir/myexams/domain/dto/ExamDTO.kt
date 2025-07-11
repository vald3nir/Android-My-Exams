package com.vald3nir.myexams.domain.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class ExamDTO(
    var modelId: Long? = null,
    var date: String? = null,
    var lab: String? = null,
    var totalCholesterol: Int? = null,
    var HDL: Int? = null,
    var notHDL: Int? = null,
    var LDL: Int? = null,
    var triglycerides: Int? = null,
    var uricAcid: Float? = null,
)