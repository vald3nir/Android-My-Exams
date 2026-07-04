package com.vald3nir.myexams.domain.dto

import com.vald3nir.toolkit.core.utils.extensions.getAge
import com.vald3nir.toolkit.core.utils.extensions.isValidBirthdate
import com.vald3nir.toolkit.core.utils.security.generateUUID
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileDTO(
    val id: String = generateUUID(),
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val birthday: String? = null,
    val gender: String? = null,
) {
    fun getAge() = birthday?.getAge()

    fun birthdateIsValid(): Boolean = birthday.isValidBirthdate()

    fun needCompleteProfile(): Boolean {
        return birthdateIsValid().not() || gender.isNullOrEmpty()
    }

}