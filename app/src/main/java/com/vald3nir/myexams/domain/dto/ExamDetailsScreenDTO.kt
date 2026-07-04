package com.vald3nir.myexams.domain.dto

import com.vald3nir.myexams.R

internal data class ExamDetailsScreenDTO(
    val exam: ExamDTO = ExamDTO(),
    val validation: ExamValidatedDTO = ExamValidatedDTO(),
    val fields: List<ExamDetailDTO> = listOf(
        ExamDetailDTO(
            label = R.string.total_cholesterol,
            value = exam.totalCholesterol?.toString(),
            warning = validation.totalCholesterolMessage,
        ),
        ExamDetailDTO(
            label = R.string.hdl_d,
            value = exam.hdl?.toString(),
            warning = validation.hdlMessage,
        ),
        ExamDetailDTO(
            label = R.string.no_hdl,
            value = exam.notHdl?.toString(),
            warning = validation.notHDLMessage,
        ),
        ExamDetailDTO(
            label = R.string.ldl,
            value = exam.ldl?.toString(),
            warning = validation.ldlMessage,
        ),
        ExamDetailDTO(
            label = R.string.triglycerides,
            value = exam.triglycerides?.toString(),
            warning = validation.triglyceridesMessage,
        ),
        ExamDetailDTO(
            label = R.string.uric_acid,
            value = exam.uricAcid?.toString(),
            warning = validation.uricAcidMessage,
        ),
    ),
)