package com.vald3nir.myexams.domain.mappers

import com.vald3nir.myexams.db.model.ProfileModel
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject

internal fun String.toModel(): ProfileModel = fromJsonToObject<ProfileModel>(this)
internal fun ProfileDTO.toModel() = ProfileModel(
    id = this.modelId,
    email = this.email,
    userImage = this.userImage,
    birthday = this.birthday,
    gender = this.gender,
)

internal fun ProfileModel.toDTO() = ProfileDTO(
    modelId = this.id,
    email = this.email,
    userImage = this.userImage,
    birthday = this.birthday,
    gender = this.gender,
)