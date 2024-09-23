package com.vald3nir.my_exams.domain.mappers

import com.vald3nir.my_exams.data.database.entities.ExamEntity
import com.vald3nir.my_exams.data.dto.ExamDTO

fun List<ExamDTO>.toMap() = this.map {
    mapOf(
        "date" to it.date,
        "total_cholesterol" to it.totalCholesterol,
        "HDL_D" to it.HDL_D,
        "NOT_HDL" to it.NOT_HDL,
        "LDL" to it.LDL,
        "triglycerides" to it.triglycerides,
        "uric_acid" to it.uricAcid,
    )
}

fun ExamEntity.asDTO(): ExamDTO {
    return ExamDTO(
        id = id,
        date = date,
        totalCholesterol = totalCholesterol,
        HDL_D = HDL_D,
        NOT_HDL = NOT_HDL,
        LDL = LDL,
        triglycerides = triglycerides,
        uricAcid = uricAcid
    )
}

fun ExamDTO.asEntity(): ExamEntity {
    return ExamEntity(
        id = id,
        date = date,
        totalCholesterol = totalCholesterol,
        HDL_D = HDL_D,
        NOT_HDL = NOT_HDL,
        LDL = LDL,
        triglycerides = triglycerides,
        uricAcid = uricAcid,
    )
}

fun List<ExamEntity>?.asDTO(): List<ExamDTO> = this?.map { it.asDTO() } ?: emptyList()
fun List<ExamDTO>?.asEntity(): List<ExamEntity> = this?.map { it.asEntity() } ?: emptyList()