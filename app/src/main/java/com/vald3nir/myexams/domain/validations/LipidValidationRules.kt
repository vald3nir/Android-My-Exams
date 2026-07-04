package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.GenderEnum

private const val PARAM_TOTAL_CHOLESTEROL_ADULT = 190
private const val PARAM_TOTAL_CHOLESTEROL_TEENAGER = 170
private const val PARAM_TOTAL_CHOLESTEROL_CHILD = 170
private const val PARAM_HDL_ADULT_MALE = 40
private const val PARAM_HDL_ADULT_FEMALE = 50
private const val PARAM_HDL_TEENAGER = 45
private const val PARAM_HDL_CHILD = 45
private const val PARAM_NOT_HDL_ADULT = 160
private const val PARAM_NOT_HDL_UNDER_20 = 120
private const val PARAM_LDL_ADULT = 130
private const val PARAM_LDL_UNDER_20 = 110
private const val PARAM_TRIGLYCERIDES_ADULT = 150
private const val PARAM_TRIGLYCERIDES_TEENAGER = 90
private const val PARAM_TRIGLYCERIDES_CHILD = 75

internal data class LipidValidationParams(
    val totalCholesterolMax: Int,
    val hdlMin: Int,
    val notHdlMax: Int,
    val ldlMax: Int,
    val triglyceridesMax: Int,
)

internal fun ProfileDTO.getLipidValidationParams(): LipidValidationParams {
    val age = this.getAge() ?: 0
    val isMale = this.gender == GenderEnum.MALE.description

    return when (age) {
        in 0..9 -> LipidValidationParams(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_CHILD,
            hdlMin = PARAM_HDL_CHILD,
            notHdlMax = PARAM_NOT_HDL_UNDER_20,
            ldlMax = PARAM_LDL_UNDER_20,
            triglyceridesMax = PARAM_TRIGLYCERIDES_CHILD
        )

        in 10..19 -> LipidValidationParams(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_TEENAGER,
            hdlMin = PARAM_HDL_TEENAGER,
            notHdlMax = PARAM_NOT_HDL_UNDER_20,
            ldlMax = PARAM_LDL_UNDER_20,
            triglyceridesMax = PARAM_TRIGLYCERIDES_TEENAGER
        )

        else -> LipidValidationParams(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_ADULT,
            hdlMin = if (isMale) PARAM_HDL_ADULT_MALE else PARAM_HDL_ADULT_FEMALE,
            notHdlMax = PARAM_NOT_HDL_ADULT,
            ldlMax = PARAM_LDL_ADULT,
            triglyceridesMax = PARAM_TRIGLYCERIDES_ADULT
        )
    }
}
