package com.vald3nir.myexams.presentation.features.exams.details

import androidx.annotation.StringRes
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO

internal data class ExamDetailsUiModel(
    val exam: ExamDTO = ExamDTO(),
    val validation: ExamValidatedDTO = ExamValidatedDTO(),
    val fields: List<ItemExamDetailsUiModel> = listOf(
        ItemExamDetailsUiModel(
            label = R.string.total_cholesterol,
            value = exam.totalCholesterol?.toString(),
            warning = validation.totalCholesterolMessage,
        ),
        ItemExamDetailsUiModel(
            label = R.string.hdl_d,
            value = exam.hdl?.toString(),
            warning = validation.hdlMessage,
        ),
        ItemExamDetailsUiModel(
            label = R.string.no_hdl,
            value = exam.notHdl?.toString(),
            warning = validation.notHDLMessage,
        ),
        ItemExamDetailsUiModel(
            label = R.string.ldl,
            value = exam.ldl?.toString(),
            warning = validation.ldlMessage,
        ),
        ItemExamDetailsUiModel(
            label = R.string.triglycerides,
            value = exam.triglycerides?.toString(),
            warning = validation.triglyceridesMessage,
        ),
        ItemExamDetailsUiModel(
            label = R.string.uric_acid,
            value = exam.uricAcid?.toString(),
            warning = validation.uricAcidMessage,
        ),
    ),
)

internal data class ItemExamDetailsUiModel(
    @StringRes val label: Int,
    val value: String?,
    val warning: String?,
)