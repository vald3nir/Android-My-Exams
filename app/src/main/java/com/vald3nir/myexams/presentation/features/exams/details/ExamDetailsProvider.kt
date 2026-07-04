package com.vald3nir.myexams.presentation.features.exams.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO

internal class ExamDetailsProvider : PreviewParameterProvider<ExamDetailsUiModel> {
    override val values: Sequence<ExamDetailsUiModel> = sequenceOf(
        ExamDetailsUiModel(
            exam = ExamDTO(
                date = "05/08/2026",
                lab = "Laboratorio Central",
                totalCholesterol = 220,
                hdl = 36,
                notHdl = 184,
                ldl = 145,
                triglycerides = 180,
                uricAcid = 7.5,
            ),
            validation = ExamValidatedDTO(
                alertsSize = 4,
                totalCholesterolMessage = "O valor apropriado e ser menor ou igual a 190",
                hdlMessage = "O valor apropriado e ser maior ou igual a 40",
                ldlMessage = "O valor apropriado e ser menor ou igual a 130",
                uricAcidMessage = "O valor apropriado e ser entre 3.5 e 7.2",
            )
        )
    )
}
