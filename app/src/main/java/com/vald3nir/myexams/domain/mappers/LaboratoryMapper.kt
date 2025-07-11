package com.vald3nir.myexams.domain.mappers

import com.vald3nir.myexams.db.model.LaboratoryModel
import com.vald3nir.myexams.domain.dto.LaboratoryDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject

internal fun List<String?>.toLaboratoryModel(): List<LaboratoryModel> {
    val list = mutableListOf<LaboratoryModel>()
    this.forEach { dataJson ->
        if (!dataJson.isNullOrEmpty()) {
            list.add(fromJsonToObject<LaboratoryModel>(dataJson))
        }
    }
    return list
}

internal fun LaboratoryModel.toDTO() = LaboratoryDTO(name = this.name)