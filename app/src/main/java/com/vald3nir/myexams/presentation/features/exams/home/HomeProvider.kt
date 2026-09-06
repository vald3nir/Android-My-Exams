package com.vald3nir.myexams.presentation.features.exams.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class HomeProvider : PreviewParameterProvider<List<ItemHomeUIModel>> {
    override val values: Sequence<List<ItemHomeUIModel>> = sequenceOf(MockExams.lists())
}

private object MockExams {

    fun lists() = listOf(
        ItemHomeUIModel(
            idExam = "exam-001",
            date = "03/07/2026",
            lab = "Laboratório São Lucas",
        ),
        ItemHomeUIModel(
            idExam = "exam-002",
            date = "18/06/2026",
            lab = "Laboratório Vida+",
        ),
        ItemHomeUIModel(
            idExam = "exam-003",
            date = "18/06/2026",
        ),
        ItemHomeUIModel(
            idExam = "exam-004",
            date = "22/05/2026",
            lab = "Laboratório Central",
        )
    )
}