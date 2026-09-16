package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun PdfStepContent(
    title: String,
    description: String,
    onSelectPdf: () -> Unit
) {
    Column {
        StepHeader(title = title, description = description)
        ToolkitSpaceHeight()
        ToolkitOutlinedButton(
            modifier = Modifier
                .padding(horizontal = ToolkitSpacingMd)
                .fillMaxWidth(),
            onClick = onSelectPdf,
            text = stringResource(R.string.create_exam_step_pdf_select_button),
        )
        ToolkitSpaceHeight(ToolkitSpacingMd)
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = stringResource(R.string.create_exam_step_pdf_optional_description),
            style = ToolkitTextStyle.BodySmall,
        )
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 250.dp)) {
        PdfStepContent(
            title = stringResource(R.string.create_exam_step_pdf_title),
            description = stringResource(R.string.create_exam_step_pdf_description),
            onSelectPdf = {},
        )
    }
}