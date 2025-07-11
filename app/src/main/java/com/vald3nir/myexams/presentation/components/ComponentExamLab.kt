package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentExamLab(colors: ScreenColorSchema, exam: ExamDTO, onClick: () -> Unit = {}) {
    MinSpaceHeight()
    ToolkitCard(backgroundColor = colors.overlayColor, onClick = onClick) {
        ToolkitRow {
            ToolkitText.Label(
                text = stringResource(R.string.lab_),
                textColor = colors.textOverlayColor
            )
            ToolkitText.Label(
                text = exam.lab.orEmpty(),
                textColor = colors.textOverlayColor
            )
        }
    }
}