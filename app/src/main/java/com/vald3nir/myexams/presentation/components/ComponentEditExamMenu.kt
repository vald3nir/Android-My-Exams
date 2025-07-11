package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.menus.ToolkitBottomSheetComponent
import com.vald3nir.toolkit.compose.components.menus.ToolkitItemBottomSheet
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
internal fun ComponentEditExamMenu(
    colors: ScreenColorSchema,
    onEditExamLab: () -> Unit = {},
    onEditExamDate: () -> Unit = {},
    onEditExamFields: () -> Unit = {},
    onDeleteExam: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    ToolkitBottomSheetComponent(
        colors = colors,
        items = listOf(
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.Science,
                title = stringResource(R.string.edit_exam_lab),
                onClick = onEditExamLab
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.DateRange,
                title = stringResource(R.string.edit_exam_date),
                onClick = onEditExamDate
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.Edit,
                title = stringResource(R.string.edit_exam_fields),
                onClick = onEditExamFields
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.Delete,
                title = stringResource(R.string.delete_exam),
                onClick = onDeleteExam
            )
        ),
        onDismissRequest = onDismissRequest,
    )
}