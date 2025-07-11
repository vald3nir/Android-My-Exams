package com.vald3nir.myexams.domain.dto

import com.vald3nir.toolkit.helpers.utils.getAge
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileDTO(
    val modelId: Long = 0,
    val email: String? = null,
    val userImage: String? = null,
    val birthday: String? = null,
    val gender: String? = null,
) {
    fun getAge() = birthday?.getAge()

    fun readyToSave() = birthday?.isNotEmpty() == true && gender?.isNotEmpty() == true
}