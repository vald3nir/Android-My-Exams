package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.MinSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitLinkButton
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.calendar.buildToolkitDatePickerDialog
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentSelectExamDate(
    selectedDate: String = "",
    onSelectDate: (String) -> Unit = {},
    colors: ScreenColorSchema,
) {
    val context = LocalContext.current
    val datePickerDialog = buildToolkitDatePickerDialog(context = context, onSelect = onSelectDate)
    MinSpaceHeight()

    ToolkitText.Subtitle(
        text = stringResource(R.string.select_exam_date_description),
        textColor = colors.textColor
    )

    DefaultSpaceHeight()

    if (selectedDate.isNotEmpty()) {
        ToolkitText.Label(
            text = stringResource(R.string.selected_date_, selectedDate),
            textColor = colors.textColor
        )
    }

    DefaultSpaceHeight()

    ToolkitLinkButton(
        onClick = { datePickerDialog.show() },
        btnLabel = stringResource(R.string.select_date),
        labelColor = colors.linkColor
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    Column {
        ComponentSelectExamDate(
            selectedDate = "25/05/2025",
            colors = appMyExamsTheme()
        )
    }
}