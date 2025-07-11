package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.mock.examMock1
import com.vald3nir.myexams.domain.mock.profileMock
import com.vald3nir.myexams.domain.validations.validate
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentExamResume(
    exam: ExamDTO,
    profile: ProfileDTO?,
    colors: ScreenColorSchema,
    onClickSeeExam: (Long?) -> Unit = {},
) {
    val validation = exam.validate(profile)
    ToolkitCard(
        backgroundColor = colors.overlayColor,
        onClick = { onClickSeeExam(exam.modelId) },
    ) {
        Column {
            ToolkitRow {
                ToolkitText.Label(
                    text = stringResource(R.string.date), textColor = colors.textOverlayColor
                )
                ToolkitText.Label(
                    text = exam.date.orEmpty(), textColor = colors.textOverlayColor
                )
                DefaultSpaceWidth()
                DefaultSpaceWidth()
                ToolkitText.Label(
                    text = stringResource(R.string.status_altered, validation.alertsSize),
                    textColor = if (validation.alertsSize > 0) colors.warningTextColor else colors.textOverlayColor
                )
                ToolkitIcon(
                    imageVector = ToolkitIcons.ChevronRight,
                    tint = colors.iconOverlayTint,
                )
            }
            ToolkitRow {
                ToolkitText.Label(
                    text = stringResource(R.string.lab_),
                    textColor = colors.textOverlayColor
                )
                DefaultSpaceWidth()
                ToolkitText.Label(
                    text = exam.lab.orEmpty(),
                    textColor = colors.textOverlayColor
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    Column {
        ComponentExamResume(
            exam = examMock1,
            profile = profileMock,
            colors = appMyExamsTheme()
        )
    }
}