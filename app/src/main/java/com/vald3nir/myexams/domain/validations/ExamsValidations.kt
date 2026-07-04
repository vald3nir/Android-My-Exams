package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO

internal fun validateExam(exam: ExamDTO?, profile: ProfileDTO?): ExamValidatedDTO {
    if (profile == null || exam == null) return ExamValidatedDTO()
    val lipidParams = profile.getLipidValidationParams()
    val validation = ExamValidatedDTO()
    validation.validateTotalCholesterol(exam, lipidParams.totalCholesterolMax)
    validation.validateHDL(exam, lipidParams.hdlMin)
    validation.validateNotHDL(exam, lipidParams.notHdlMax)
    validation.validateLDL(exam, lipidParams.ldlMax)
    validation.validateTriglycerides(exam, lipidParams.triglyceridesMax)
    validation.validateUricAcid(exam, profile)
    return validation
}