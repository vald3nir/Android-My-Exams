package com.vald3nir.toolkit.auth.validations

import android.content.Context
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.helpers.validations.isEmailValid
import com.vald3nir.toolkit.helpers.validations.isPasswordValid

fun Context.validateEmail(email: String): String? {
    return when {
        email.isBlank() -> getString(R.string.error_field_required)
        !email.isEmailValid() -> getString(R.string.error_email_is_invalid)
        else -> null
    }
}

fun Context.validatePassword(password: String, confirmPassword: String): String? {
    return when {
        password.isBlank() || confirmPassword.isBlank() -> getString(R.string.error_field_required)
        password != confirmPassword -> getString(R.string.error_passwords_dont_match)
        else -> null
    }
}

fun Context.validatePassword(password: String): String? {
    return if (password.isPasswordValid(maxPasswordSize = 99)) null else getString(R.string.error_field_required)
}
