package com.vald3nir.myexams.presentation.features.exams.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.domain.dto.ExamsHomeScreenDTO

internal class HomeProvider : PreviewParameterProvider<List<ExamsHomeScreenDTO>> {
    override val values: Sequence<List<ExamsHomeScreenDTO>> = sequenceOf(MockExams.lists())
}

private object MockExams {

    fun lists() = listOf(
        ExamsHomeScreenDTO(
            id = "exam-001",
            date = "03/07/2026",
            lab = "Laboratório São Lucas",
            hasAlerts = false,
        ),
        ExamsHomeScreenDTO(
            id = "exam-002",
            date = "18/06/2026",
            lab = "Laboratório Vida+",
            hasAlerts = true,
        ),
        ExamsHomeScreenDTO(
            id = "exam-003",
            date = "18/06/2026",
            hasAlerts = true,
        ),
        ExamsHomeScreenDTO(
            id = "exam-004",
            date = "22/05/2026",
            lab = "Laboratório Central",
            hasAlerts = false,
        )
    )
}