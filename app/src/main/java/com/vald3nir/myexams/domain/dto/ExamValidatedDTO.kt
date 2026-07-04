package com.vald3nir.myexams.domain.dto

internal data class ExamValidatedDTO(
    var alertsSize: Int = 0,
    var totalCholesterolMessage: String? = null,
    var hdlMessage: String? = null,
    var notHDLMessage: String? = null,
    var ldlMessage: String? = null,
    var triglyceridesMessage: String? = null,
    var uricAcidMessage: String? = null,
) {
    fun hasAlerts(): Boolean = alertsSize > 0
}