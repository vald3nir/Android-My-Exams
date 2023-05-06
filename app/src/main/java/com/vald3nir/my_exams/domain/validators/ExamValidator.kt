package com.vald3nir.my_exams.domain.validators

import com.vald3nir.core.extensions.orZero


fun validateTotalCholesterol(value: Int?): ValidatorStatus {
    return if (value.orZero() >= 190) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser menor que 190"
        )
    } else {
        ValidatorStatus()
    }
}

fun validateHDL(value: Int?): ValidatorStatus {
    return if (value in 1..39) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser maior ou igual a 40"
        )
    } else {
        ValidatorStatus()
    }
}

fun validateNotHDL(value: Int?): ValidatorStatus {
    return if (value.orZero() >= 160) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser menor que 160"
        )
    } else {
        ValidatorStatus()
    }
}

fun validateLDL(value: Int?): ValidatorStatus {
    return if (value.orZero() >= 130) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser menor que 130"
        )
    } else {
        ValidatorStatus()
    }
}

fun validateTriglycerides(value: Int?): ValidatorStatus {
    return if (value.orZero() > 150) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser menor ou igual a 150"
        )
    } else {
        ValidatorStatus()
    }
}

fun validateUricAcid(value: Float?): ValidatorStatus {
    return if (value.orZero() > 7.2f) {
        ValidatorStatus(
            passed = false,
            description = "O valor apropriado é ser menor ou igual a 7.2"
        )
    } else {
        ValidatorStatus()
    }
}