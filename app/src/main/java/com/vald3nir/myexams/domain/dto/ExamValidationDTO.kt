package com.vald3nir.myexams.domain.dto

internal data class ExamValidationDTO(
    var alertsSize: Int = 0,
    var totalCholesterolMessage: String? = null,
    var hdlMessage: String? = null,
    var notHDLMessage: String? = null,
    var ldlMessage: String? = null,
    var triglyceridesMessage: String? = null,
)