package com.vald3nir.myexams.presentation.features.exams.create

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.domain.dto.ExamDTO

internal class CreateExamProvider : PreviewParameterProvider<CreateExamUiModel> {
    override val values: Sequence<CreateExamUiModel> = sequenceOf(
        CreateExamUiModel(
            exam = ExamDTO(
                id = "exam-001",
                date = "03/07/2026",
                lab = "Laboratório São Lucas",
            ),
            labs = listOf("Laboratório São Lucas", "Laboratório Vida+", "Laboratório Central"),
            topLabs = listOf("Laboratório São Lucas", "Laboratório Vida+")
        )
    )
}