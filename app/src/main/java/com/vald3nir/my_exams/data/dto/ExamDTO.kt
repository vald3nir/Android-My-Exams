package com.vald3nir.my_exams.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExamDTO(
    var id: Long = 0,
    @SerialName("date") var date: String? = null,
    @SerialName("total_cholesterol") var totalCholesterol: Int? = null,
    @SerialName("HDL_D") var HDL_D: Int? = null,
    @SerialName("NOT_HDL") var NOT_HDL: Int? = null,
    @SerialName("LDL") var LDL: Int? = null,
    @SerialName("triglycerides") var triglycerides: Int? = null,
    @SerialName("uric_acid") var uricAcid: Float? = null,
)