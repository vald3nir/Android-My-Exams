package com.vald3nir.toolkit.auth.presentation

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_LOGIN_RESPONSE
import com.vald3nir.toolkit.auth.AuthLibLoginResponseType
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor() : BaseViewModel() {

    var serverClientId: String = ""

    fun loginWithGoogle(activity: Activity?) {
        launchWithScope {
            FirebaseAuthenticator.loginWithGoogle(
                activity = activity,
                serverClientId = serverClientId,
                onLoginSuccess = {
                    finishAuthentication(activity, AuthLibLoginResponseType.SUCCESS)
                },
                onLoginError = {
                    updateViewState(BaseScreenState.Loading(false))
                    updateViewState(BaseScreenState.ShowMessage(it?.message.toString()))
                }
            )
        }
    }

    fun createUserWithEmailAndPassword(activity: Activity?, email: String, password: String) {
        updateViewState(BaseScreenState.Loading(true))
        FirebaseAuthenticator.createUserWithEmailAndPassword(
            activity = activity,
            email = email,
            password = password,
            onCreateUserSuccess = {
                loginWithEmailAndPassword(activity = activity, email = email, password = password)
            },
            onLoginError = {
                updateViewState(BaseScreenState.Loading(false))
                updateViewState(BaseScreenState.ShowMessage(it?.message))
            },
        )
    }

    fun loginWithEmailAndPassword(activity: Activity?, email: String, password: String) {
        launchWithScope {
            FirebaseAuthenticator.loginWithEmailAndPassword(
                activity = activity,
                email = email,
                password = password,
                onLoginSuccess = {
                    finishAuthentication(activity, AuthLibLoginResponseType.SUCCESS)
                },
                onLoginError = {
                    updateViewState(BaseScreenState.Loading(false))
                    updateViewState(BaseScreenState.ShowMessage(it?.message))
                }
            )
        }
    }

    fun useFakeData(activity: Activity?) {
        finishAuthentication(activity, AuthLibLoginResponseType.USE_TRIAL)
    }

    private fun finishAuthentication(activity: Activity?, responseType: AuthLibLoginResponseType) {
        val resultIntent = Intent().apply {
            putExtra(AUTH_LIB_PARAM_LOGIN_RESPONSE, responseType.name)
        }
        activity?.apply {
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}