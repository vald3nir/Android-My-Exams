package com.vald3nir.myexams.domain.dto

internal data class ExamValidatedDTO(
    var alertsSize: Int = 0,
    var totalCholesterolMessage: String? = null,
    var HDLMessage: String? = null,
    var notHDLMessage: String? = null,
    var LDLMessage: String? = null,
    var triglyceridesMessage: String? = null,
    var uricAcidMessage: String? = null,
)