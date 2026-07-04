package com.vald3nir.myexams.domain.dto

import com.vald3nir.toolkit.core.utils.extensions.getAge

internal data class UserDTO(
    val modelId: Long = 0,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val birthday: String? = null,
    val gender: String? = null,
) {
    fun getAge() = birthday?.getAge()

    fun readyToSave() = name?.isNotBlank() == true && birthday?.isNotBlank() == true && gender?.isNotBlank() == true
}