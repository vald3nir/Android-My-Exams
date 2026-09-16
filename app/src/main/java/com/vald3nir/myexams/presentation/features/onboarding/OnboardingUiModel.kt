package com.vald3nir.myexams.presentation.features.onboarding

import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.AppScreenRedirect
import com.vald3nir.toolkit.core.utils.extensions.isValidBirthdate

internal data class OnboardingUiModel(
    val isUserLogged: Boolean = false,
    var profile: ProfileDTO? = null,
) {
    val redirect: AppScreenRedirect = when {
        !isUserLogged || profile == null -> AppScreenRedirect.AUTH
        needCompleteProfile() -> AppScreenRedirect.COMPLETE_PROFILE
        else -> AppScreenRedirect.HOME
    }

    fun needCompleteProfile(): Boolean {
        return birthdateIsValid().not() || profile?.gender.isNullOrEmpty()
    }

    fun birthdateIsValid(): Boolean {
        return profile?.birthday.isValidBirthdate()
    }
}