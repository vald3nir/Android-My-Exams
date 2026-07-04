package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitLinkButton
import com.vald3nir.toolkit.designsystem.components.dialogs.toolkitDatePickerDialog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
internal fun SelectExamDate(
    selectedDate: String = "",
    onSelectDate: (String) -> Unit = {},
) {
    Column(modifier = Modifier.padding(horizontal = ToolkitSpacingMd)) {
        val context = LocalContext.current
        val datePickerDialog = toolkitDatePickerDialog(context = context, onSelect = onSelectDate)
        ToolkitText(
            text = stringResource(R.string.select_exam_date_description),
            style = ToolkitTextStyle.TitleMedium
        )
        if (selectedDate.isNotEmpty()) {
            ToolkitSpaceHeight()
            ToolkitText(
                text = stringResource(R.string.selected_date_, selectedDate),
                style = ToolkitTextStyle.TitleSmall
            )
        }
        ToolkitSpaceHeight()
        ToolkitLinkButton(
            onClick = { datePickerDialog.show() },
            label = stringResource(R.string.select_date),
        )
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 120.dp)) {
        SelectExamDate(
            selectedDate = "25/05/2025"
        )
    }
}