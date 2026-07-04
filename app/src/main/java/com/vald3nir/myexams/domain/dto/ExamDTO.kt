package com.vald3nir.myexams.domain.dto

import com.vald3nir.toolkit.core.utils.security.generateUUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ExamDTO(
    val id: String = generateUUID(),
    val owner: String? = null,
    val date: String? = null,
    val lab: String? = null,
    @SerialName("total_cholesterol") val totalCholesterol: Int? = null,
    @SerialName("hdl") val hdl: Int? = null,
    @SerialName("not_hdl") val notHdl: Int? = null,
    val ldl: Int? = null,
    val triglycerides: Int? = null,
    @SerialName("uric_acid") val uricAcid: Double? = null,
) {

    fun isLabValid(): Boolean = lab.isNullOrBlank().not()//todo valdenir remover

    fun isDateValid(): Boolean = date.isNullOrBlank().not()

    fun isFieldValid(): Boolean = totalCholesterol != null || hdl != null || notHdl != null || ldl != null || triglycerides != null || uricAcid != null
}