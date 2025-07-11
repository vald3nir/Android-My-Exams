package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamValidatedDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.mock.examMock1
import com.vald3nir.myexams.domain.mock.profileMock
import com.vald3nir.myexams.domain.validations.validate
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitColumn
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentExamFieldsViewer(exam: ExamDTO, profile: ProfileDTO?, colors: ScreenColorSchema, onClick: () -> Unit = {}) {
    val validation: ExamValidatedDTO = exam.validate(profile)
    ToolkitCard(backgroundColor = colors.overlayColor, onClick = onClick) {
        ToolkitColumn {
            exam.totalCholesterol?.let { totalCholesterol ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.total_cholesterol),
                    value = totalCholesterol.toString(),
                    errorMessage = validation.totalCholesterolMessage,
                )
                MinSpaceHeight()
            }
            exam.HDL?.let { hdl ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.hdl_d),
                    value = hdl.toString(),
                    errorMessage = validation.HDLMessage,
                )
                MinSpaceHeight()
            }
            exam.notHDL?.let { notHDL ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.no_hdl),
                    value = notHDL.toString(),
                    errorMessage = validation.notHDLMessage,
                )
                MinSpaceHeight()
            }
            exam.LDL?.let { ldl ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.ldl),
                    value = ldl.toString(),
                    errorMessage = validation.LDLMessage,
                )
                MinSpaceHeight()
            }
            exam.triglycerides?.let { triglycerides ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.triglycerides),
                    value = triglycerides.toString(),
                    errorMessage = validation.triglyceridesMessage,
                )
                MinSpaceHeight()
            }
            exam.uricAcid?.let { uricAcid ->
                ComponentExamFieldCell(
                    colors = colors,
                    label = stringResource(R.string.uric_acid),
                    value = uricAcid.toString(),
                    errorMessage = validation.uricAcidMessage,
                )
                DefaultSpaceHeight()
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    ComponentExamFieldsViewer(
        exam = examMock1,
        profile = profileMock,
        colors = appMyExamsTheme()
    )
}