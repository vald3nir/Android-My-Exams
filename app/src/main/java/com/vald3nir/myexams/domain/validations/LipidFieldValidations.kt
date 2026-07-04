package com.vald3nir.myexams.domain.validations

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO

internal fun ExamValidatedDTO.validateTotalCholesterol(exam: ExamDTO, referenceValue: Int) {
    exam.totalCholesterol?.let { totalCholesterol ->
        if (totalCholesterol > referenceValue) {
            alertsSize++
            totalCholesterolMessage = "O valor apropriado é ser menor ou igual a $referenceValue"
        }
    }
}

internal fun ExamValidatedDTO.validateHDL(exam: ExamDTO, referenceValue: Int) {
    exam.hdl?.let { hdl ->
        if (hdl < referenceValue) {
            alertsSize++
            hdlMessage = "O valor apropriado é ser maior ou igual a $referenceValue"
        }
    }
}

internal fun ExamValidatedDTO.validateNotHDL(exam: ExamDTO, referenceValue: Int) {
    exam.notHdl?.let { notHDL ->
        if (notHDL > referenceValue) {
            alertsSize++
            notHDLMessage = "O valor apropriado é ser menor ou igual a $referenceValue"
        }
    }
}

internal fun ExamValidatedDTO.validateLDL(exam: ExamDTO, referenceValue: Int) {
    exam.ldl?.let { ldl ->
        if (ldl > referenceValue) {
            alertsSize++
            ldlMessage = "O valor apropriado é ser menor ou igual a $referenceValue"
        }
    }
}

internal fun ExamValidatedDTO.validateTriglycerides(exam: ExamDTO, referenceValue: Int) {
    exam.triglycerides?.let { triglycerides ->
        if (triglycerides > referenceValue) {
            alertsSize++
            triglyceridesMessage = "O valor apropriado é ser menor ou igual a $referenceValue"
        }
    }
}
