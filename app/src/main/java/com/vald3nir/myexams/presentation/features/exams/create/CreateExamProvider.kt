package com.vald3nir.myexams.presentation.features.exams.create

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.core.utils.extensions.toLocalDateTime

internal class CreateExamProvider : PreviewParameterProvider<CreateExamUiModel> {
    override val values: Sequence<CreateExamUiModel> = sequenceOf(
        CreateExamUiModel(
            exam = ExamDTO(
                id = "exam-001",
                date = "03/07/2026".toLocalDateTime(),
                lab = "Laboratório São Lucas",
            ),
            labs = listOf("Laboratório São Lucas", "Laboratório Vida+", "Laboratório Central"),
            topLabs = listOf("Laboratório São Lucas", "Laboratório Vida+")
        )
    )
}