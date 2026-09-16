package com.vald3nir.myexams.domain.dto

internal data class LipidValidationParamsDTO(
    val totalCholesterolMax: Int,
    val hdlMin: Int,
    val notHdlMax: Int,
    val ldlMax: Int,
    val triglyceridesMax: Int,
) {
    // Alert messages for each parameter
    val totalCholesterolMessage: String = "O valor apropriado é ser menor ou igual a $totalCholesterolMax"
    val hdlMessage: String = "O valor apropriado é ser maior ou igual a $hdlMin"
    val notHDLMessage: String = "O valor apropriado é ser menor ou igual a $notHdlMax"
    val ldlMessage: String = "O valor apropriado é ser menor ou igual a $ldlMax"
    val triglyceridesMessage: String = "O valor apropriado é ser menor ou igual a $triglyceridesMax"
}