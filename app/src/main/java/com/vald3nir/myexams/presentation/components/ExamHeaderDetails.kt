package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun ExamHeaderDetails(exam: ExamDTO) {
    val textStyle = ToolkitTextStyle.TitleSmall
    ToolkitCard(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Column(
                modifier = Modifier.padding(ToolkitSpacingMd),
                verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ToolkitText(text = stringResource(R.string.exam_details_date_label), style = textStyle)
                    ToolkitText(text = exam.date ?: stringResource(R.string.common_not_available), style = textStyle)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ToolkitText(text = stringResource(R.string.exam_details_lab_label), style = textStyle)
                    ToolkitText(text = exam.lab ?: stringResource(R.string.home_screen_laboratory_not_specified), style = textStyle)
                }
            }
        }
    )
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ExamHeaderDetails(exam = ExamDTO(date = "2023-01-01", lab = "Lab XYZ"))
    }
}