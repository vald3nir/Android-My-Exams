package com.vald3nir.toolkit.auth.presentation

import androidx.navigation.NavController
import com.vald3nir.toolkit.auth.presentation.navigaton.AuthScreenRoute
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

internal data class AuthScope(
    val activity: BaseActivity,
    val viewModel: AuthViewModel,
    val navController: NavController
) : BaseScreenScope(viewModel, navController) {

    val onClickLoginWithGoogle: () -> Unit = {
        viewModel.loginWithGoogle(activity = activity)
    }

    val onClickUseFakeData: () -> Unit = {
        viewModel.useFakeData(activity = activity)
    }

    val onClickLogin: (email: String, password: String) -> Unit = { email, password ->
        viewModel.loginWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
        )
    }

    val onClickSignUp: (email: String, password: String) -> Unit = { email, password ->
        viewModel.createUserWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
        )
    }

    val onClickRedirectToLogin: () -> Unit = {
        navController.navigate(AuthScreenRoute.Login)
    }

    val onClickRedirectToSignUp: () -> Unit = {
        navController.navigate(AuthScreenRoute.SignUp)
    }
}

internal fun enableBtnContinue(email: String, password: String, errorPasswordMessage: String?, errorEmailMessage: String?): Boolean {
    return email.isNotBlank() && password.isNotBlank() && errorPasswordMessage.isNullOrBlank() && errorEmailMessage.isNullOrBlank()
}