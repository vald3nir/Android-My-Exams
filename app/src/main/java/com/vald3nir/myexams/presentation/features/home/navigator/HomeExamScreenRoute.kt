package com.vald3nir.myexams.presentation.features.home.navigator

import kotlinx.serialization.Serializable

sealed interface HomeExamScreenRoute {
    @Serializable
    data object Home : HomeExamScreenRoute

    @Serializable
    data class ExamDetail(val idExam: Long?) : HomeExamScreenRoute

    @Serializable
    data class EditLab(val idExam: Long?) : HomeExamScreenRoute

    @Serializable
    data class EditDate(val idExam: Long?) : HomeExamScreenRoute

    @Serializable
    data class EditExam(val idExam: Long?) : HomeExamScreenRoute
}