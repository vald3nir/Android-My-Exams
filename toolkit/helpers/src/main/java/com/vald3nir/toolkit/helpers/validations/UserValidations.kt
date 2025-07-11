package com.vald3nir.toolkit.helpers.validations

import android.util.Patterns

fun String.isUserNameValid(namesSize: Int = 2): Boolean {
    return if (this.isNotBlank()) {
        this.trim().split("").size >= namesSize
    } else false
}

fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPasswordValid(maxPasswordSize: Int = 6): Boolean {
    return this.isNotBlank() && this.length <= maxPasswordSize
}