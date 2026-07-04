package com.vald3nir.myexams.domain.dto

import com.vald3nir.myexams.domain.enums.AppScreenRedirect

internal data class AppScreenUiState(
    val isUserLogged: Boolean = false,
    val profile: ProfileDTO? = null,
) {
    val redirect: AppScreenRedirect = when {
        !isUserLogged -> {
            AppScreenRedirect.AUTH
        }
        profile == null -> {
            AppScreenRedirect.LOADING
        }
        profile.needCompleteProfile() -> {
            AppScreenRedirect.COMPLETE_PROFILE
        }
        else -> {
            AppScreenRedirect.HOME
        }
    }
}