package com.vald3nir.myexams.presentation.features.profile.home

import com.vald3nir.myexams.domain.dto.ProfileDTO

internal data class HomeProfileUiModel(
    val profile: ProfileDTO = ProfileDTO(),
    val listener: HomeProfileUiModelListener? = null
) {
    fun onChangeName(name: String) {
        listener?.onEditProfile(profile.copy(name = name))
    }

    fun onChangeGender(gender: String) {
        listener?.onEditProfile(profile.copy(gender = gender))
    }

    fun onChangeBirthDate(birthday: String) {
        listener?.onEditProfile(profile.copy(birthday = birthday))
    }

    fun logout() {
        listener?.logout()
    }
}

internal interface HomeProfileUiModelListener {
    fun onEditProfile(profile: ProfileDTO)
    fun logout()
}