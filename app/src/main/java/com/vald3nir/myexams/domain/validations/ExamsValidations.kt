package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.GenderEnum


private const val PARAM_TOTAL_CHOLESTEROL_ADULT = 190
private const val PARAM_TOTAL_CHOLESTEROL_TEENAGER = 170
private const val PARAM_TOTAL_CHOLESTEROL_CHILD = 170
private const val PARAM_HDL_ADULT = 40
private const val PARAM_HDL_TEENAGER = 45
private const val PARAM_HDL_CHILD = 45
private const val PARAM_TRIGLYCERIDES_ADULT = 150
private const val PARAM_TRIGLYCERIDES_TEENAGER = 90
private const val PARAM_TRIGLYCERIDES_CHILD = 75
private const val PARAM_URIC_ACID_MALE_MIN = 3.5
private const val PARAM_URIC_ACID_MALE_MAX = 7.2
private const val PARAM_URIC_ACID_FEMALE_MIN = 2.6
private const val PARAM_URIC_ACID_FEMALE_MAX = 6.0

internal fun ExamDTO.validate(profile: ProfileDTO?): ExamValidatedDTO {
    return if (profile == null) ExamValidatedDTO()
    else when (profile.getAge()) {
        in 0..9 -> this.validateForChild(profile)
        in 10..19 -> this.validateForTeenager(profile)
        else -> this.validateForAdult(profile)
    }
}

private enum class FieldLevelEnum(val level: String) {
    LOW("Baixo"), NORMAL("Normal"), INTERMEDIARY("Intermediário"), HIGH("Alto"), VERY_HIGH("Muito Alto")
}

private fun ExamDTO.validateForAdult(profile: ProfileDTO): ExamValidatedDTO {
    val validation = ExamValidatedDTO()
    validation.validateTotalCholesterol(this, PARAM_TOTAL_CHOLESTEROL_ADULT)
    validation.validateHDL(this, PARAM_HDL_ADULT)
    validation.validateNotHDL(this)
    validation.validateLDL(this)
    validation.validateTriglycerides(this, PARAM_TRIGLYCERIDES_ADULT)
    validation.validateUricAcid(this, profile)
    return validation
}

private fun ExamDTO.validateForChild(profile: ProfileDTO): ExamValidatedDTO {
    val validation = ExamValidatedDTO()
    validation.validateTotalCholesterol(this, PARAM_TOTAL_CHOLESTEROL_TEENAGER)
    validation.validateHDL(this, PARAM_HDL_TEENAGER)
    validation.validateNotHDL(this)
    validation.validateLDL(this)
    validation.validateTriglycerides(this, PARAM_TRIGLYCERIDES_TEENAGER)
    validation.validateUricAcid(this, profile)
    return validation
}

private fun ExamDTO.validateForTeenager(profile: ProfileDTO): ExamValidatedDTO {
    val validation = ExamValidatedDTO()
    validation.validateTotalCholesterol(this, PARAM_TOTAL_CHOLESTEROL_CHILD)
    validation.validateHDL(this, PARAM_HDL_CHILD)
    validation.validateNotHDL(this)
    validation.validateLDL(this)
    validation.validateTriglycerides(this, PARAM_TRIGLYCERIDES_CHILD)
    validation.validateUricAcid(this, profile)
    return validation
}

private fun ExamValidatedDTO.validateTotalCholesterol(exam: ExamDTO, referenceValue: Int) {
    exam.totalCholesterol?.let { totalCholesterol ->
        if (totalCholesterol >= referenceValue) {
            alertsSize++
            totalCholesterolMessage = "O valor apropriado é ser menor que $referenceValue"
        }
    }

}

private fun ExamValidatedDTO.validateHDL(exam: ExamDTO, referenceValue: Int) {
    exam.HDL?.let { hdl ->
        if (hdl < referenceValue) {
            alertsSize++
            HDLMessage = "O valor apropriado é ser maior ou igual a $referenceValue"
        }
    }
}

private fun Int.getNotHDLLevel(): FieldLevelEnum {
    return when (this) {
        in 0..80 -> FieldLevelEnum.VERY_HIGH
        in 81..100 -> FieldLevelEnum.HIGH
        in 101..130 -> FieldLevelEnum.NORMAL
        else -> FieldLevelEnum.LOW
    }
}

private fun Int.getLDLLevel(): FieldLevelEnum {
    return when (this) {
        in 0..50 -> FieldLevelEnum.VERY_HIGH
        in 51..70 -> FieldLevelEnum.HIGH
        in 71..100 -> FieldLevelEnum.NORMAL
        in 101..130 -> FieldLevelEnum.INTERMEDIARY
        else -> FieldLevelEnum.LOW
    }
}

private fun ExamValidatedDTO.validateNotHDL(exam: ExamDTO) {
    exam.notHDL?.let { notHDL ->
        val notHDLLevel = notHDL.getNotHDLLevel()
        if (notHDLLevel != FieldLevelEnum.NORMAL) {
            alertsSize++
            notHDLMessage = "O nivel do Não-HDL está ${notHDLLevel.level}"
        }
    }
}

private fun ExamValidatedDTO.validateLDL(exam: ExamDTO) {
    exam.LDL?.let { ldl ->
        val ldlLevel = ldl.getLDLLevel()
        if (ldlLevel != FieldLevelEnum.NORMAL) {
            alertsSize++
            LDLMessage = "O nivel do LDL está ${ldlLevel.level}"
        }
    }
}

private fun ExamValidatedDTO.validateTriglycerides(exam: ExamDTO, referenceValue: Int) {
    exam.triglycerides?.let { triglycerides ->
        if (triglycerides < referenceValue) {
            alertsSize++
            triglyceridesMessage = "O valor apropriado é ser menor ou igual a $referenceValue"
        }
    }
}

private fun ExamValidatedDTO.validateUricAcid(exam: ExamDTO, profile: ProfileDTO) {
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