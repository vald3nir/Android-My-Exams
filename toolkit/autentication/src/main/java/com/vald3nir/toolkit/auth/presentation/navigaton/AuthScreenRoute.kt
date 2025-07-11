package com.vald3nir.toolkit.auth.presentation.navigaton

import kotlinx.serialization.Serializable

sealed interface AuthScreenRoute {

    @Serializable
    data object HomeAuth : AuthScreenRoute

    @Serializable
    data object Login : AuthScreenRoute

    @Serializable
    data object SignUp : AuthScreenRoute
}