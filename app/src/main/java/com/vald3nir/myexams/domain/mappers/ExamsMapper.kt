package com.vald3nir.myexams.domain.mappers

import com.vald3nir.myexams.db.model.ExamModel
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject

internal fun List<String?>.toExamsModel(): List<ExamModel> {
    val list = mutableListOf<ExamModel>()
    this.forEach { dataJson ->
        if (!dataJson.isNullOrEmpty()) {
            list.add(fromJsonToObject<ExamModel>(dataJson))
        }
    }
    return list
}

internal fun ExamModel.toDTO() = ExamDTO(
    modelId = id,
    date = date,
    lab = lab,
    totalCholesterol = totalCholesterol,
    HDL = HDL,
    notHDL = notHDL,
    LDL = LDL,
    triglycerides = triglycerides,
    uricAcid = uricAcid
)

internal fun ExamDTO.toModel() = ExamModel(
    date = date,
    lab = lab,
    totalCholesterol = totalCholesterol,
    HDL = HDL,
    notHDL = notHDL,
    LDL = LDL,
    triglycerides = triglycerides,
    uricAcid = uricAcid
)