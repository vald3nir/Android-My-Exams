package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.GenderEnum

private const val PARAM_URIC_ACID_MALE_MIN = 3.5
private const val PARAM_URIC_ACID_MALE_MAX = 7.2
private const val PARAM_URIC_ACID_FEMALE_MIN = 2.6
private const val PARAM_URIC_ACID_FEMALE_MAX = 6.0

internal fun ProfileDTO.getUricAcidValidationRange(): ClosedFloatingPointRange<Float> {
    return if (gender == GenderEnum.MALE.description) {
        PARAM_URIC_ACID_MALE_MIN.toFloat()..PARAM_URIC_ACID_MALE_MAX.toFloat()
    } else {
        PARAM_URIC_ACID_FEMALE_MIN.toFloat()..PARAM_URIC_ACID_FEMALE_MAX.toFloat()
    }
}

internal fun ExamValidatedDTO.validateUricAcid(exam: ExamDTO, profile: ProfileDTO) {
    exam.uricAcid?.let { uricAcid ->
        if (profile.gender == GenderEnum.MALE.description) {
            if (uricAcid !in PARAM_URIC_ACID_MALE_MIN..PARAM_URIC_ACID_MALE_MAX) {
                alertsSize++
                uricAcidMessage = "O valor apropriado é ser entre $PARAM_URIC_ACID_MALE_MIN e $PARAM_URIC_ACID_MALE_MAX"
            }
        } else {
            if (uricAcid !in PARAM_URIC_ACID_FEMALE_MIN..PARAM_URIC_ACID_FEMALE_MAX) {
                alertsSize++
                uricAcidMessage = "O valor apropriado é ser entre $PARAM_URIC_ACID_FEMALE_MIN e $PARAM_URIC_ACID_FEMALE_MAX"
            }
        }
    }
}
