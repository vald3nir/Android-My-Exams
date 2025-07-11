package com.vald3nir.myexams.presentation.features.insertExam.navigator

import kotlinx.serialization.Serializable

sealed interface InsertExamScreenRoute {
    @Serializable
    data object SelectLab : InsertExamScreenRoute

    @Serializable
    data object SelectDate : InsertExamScreenRoute

    @Serializable
    data object InsertExam : InsertExamScreenRoute
}