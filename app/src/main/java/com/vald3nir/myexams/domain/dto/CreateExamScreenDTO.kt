package com.vald3nir.myexams.domain.dto

import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.enums.CreateExamStep

internal data class CreateExamScreenDTO(
    val exam: ExamDTO = ExamDTO(),
    val labs: List<String> = emptyList(),
    val topLabs: List<String> = emptyList(),
) {
    fun isValid(): Boolean = exam.date.isNullOrBlank().not() && exam.lab.isNullOrBlank().not()

    fun isBottomButtonEnabled(step: CreateExamStep): Boolean = when (step) {
        CreateExamStep.Pdf -> true
        CreateExamStep.Date -> exam.isDateValid()
        CreateExamStep.Lab -> exam.isLabValid()
        CreateExamStep.Fields -> exam.isFieldValid()
    }

    fun bottomButtonRes(step: CreateExamStep): Int = when (step) {
        CreateExamStep.Fields -> R.string.btn_insert
        CreateExamStep.Pdf,
        CreateExamStep.Date,
        CreateExamStep.Lab -> R.string.btn_continue
    }
}