package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidationDTO
import com.vald3nir.myexams.domain.dto.LipidValidationParamsDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.GenderEnum
import com.vald3nir.toolkit.core.utils.extensions.getAge

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

internal fun ProfileDTO.getLipidValidationParams(): LipidValidationParamsDTO {
    val age = this.birthday?.getAge() ?: 0
    val isMale = this.gender == GenderEnum.MALE.description

    return when (age) {
        in 0..9 -> LipidValidationParamsDTO(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_CHILD,
            hdlMin = PARAM_HDL_CHILD,
            notHdlMax = PARAM_NOT_HDL_UNDER_20,
            ldlMax = PARAM_LDL_UNDER_20,
            triglyceridesMax = PARAM_TRIGLYCERIDES_CHILD
        )

        in 10..19 -> LipidValidationParamsDTO(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_TEENAGER,
            hdlMin = PARAM_HDL_TEENAGER,
            notHdlMax = PARAM_NOT_HDL_UNDER_20,
            ldlMax = PARAM_LDL_UNDER_20,
            triglyceridesMax = PARAM_TRIGLYCERIDES_TEENAGER
        )

        else -> LipidValidationParamsDTO(
            totalCholesterolMax = PARAM_TOTAL_CHOLESTEROL_ADULT,
            hdlMin = if (isMale) PARAM_HDL_ADULT_MALE else PARAM_HDL_ADULT_FEMALE,
            notHdlMax = PARAM_NOT_HDL_ADULT,
            ldlMax = PARAM_LDL_ADULT,
            triglyceridesMax = PARAM_TRIGLYCERIDES_ADULT
        )
    }
}

internal fun validateExam(exam: ExamDTO?, profile: ProfileDTO?): ExamValidationDTO {
    if (profile == null || exam == null) return ExamValidationDTO()
    val validation = ExamValidationDTO()
    val lipidParams = profile.getLipidValidationParams()
    validation.validateTotalCholesterol(exam, lipidParams)
    validation.validateHDL(exam, lipidParams)
    validation.validateNotHDL(exam, lipidParams)
    validation.validateLDL(exam, lipidParams)
    validation.validateTriglycerides(exam, lipidParams)
    return validation
}

private fun ExamValidationDTO.validateTotalCholesterol(exam: ExamDTO, lipidParams: LipidValidationParamsDTO) {
    exam.totalCholesterol?.let { totalCholesterol ->
        if (totalCholesterol > lipidParams.totalCholesterolMax) {
            alertsSize++
            totalCholesterolMessage = lipidParams.totalCholesterolMessage
        }
    }
}

private fun ExamValidationDTO.validateHDL(exam: ExamDTO, lipidParams: LipidValidationParamsDTO) {
    exam.hdl?.let { hdl ->
        if (hdl < lipidParams.hdlMin) {
            alertsSize++
            hdlMessage = lipidParams.hdlMessage
        }
    }
}

private fun ExamValidationDTO.validateNotHDL(exam: ExamDTO, lipidParams: LipidValidationParamsDTO) {
    exam.notHdl?.let { notHDL ->
        if (notHDL > lipidParams.notHdlMax) {
            alertsSize++
            notHDLMessage = lipidParams.notHDLMessage
        }
    }
}

private fun ExamValidationDTO.validateLDL(exam: ExamDTO, lipidParams: LipidValidationParamsDTO) {
    exam.ldl?.let { ldl ->
        if (ldl > lipidParams.ldlMax) {
            alertsSize++
            ldlMessage = lipidParams.ldlMessage
        }
    }
}

private fun ExamValidationDTO.validateTriglycerides(exam: ExamDTO, lipidParams: LipidValidationParamsDTO) {
    exam.triglycerides?.let { triglycerides ->
        if (triglycerides > lipidParams.triglyceridesMax) {
            alertsSize++
            triglyceridesMessage = lipidParams.triglyceridesMessage
        }
    }
}