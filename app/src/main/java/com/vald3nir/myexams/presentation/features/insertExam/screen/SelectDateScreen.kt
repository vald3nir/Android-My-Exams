package com.vald3nir.myexams.presentation.features.insertExam.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.presentation.components.ComponentSelectExamDate
import com.vald3nir.myexams.presentation.features.insertExam.InsertExamScope
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer

@Composable
internal fun InsertExamScope.SelectDateScreen() {
    val context = LocalContext.current
    val colors = context.loadAppColorSchema()
    var selectedDate by remember { mutableStateOf("") }
    AppTheme {
        ToolkitBaseContainer(
            backgroundColor = colors.backgroundColor,
            topBarContent = {
                ToolkitGenericToolbarComponent(
                    title = stringResource(R.string.select_exam_date),
                    colors = colors,
                    onBackClick = { onBackPressed() },
                )
            },
            content = {
                ComponentSelectExamDate(
                    selectedDate = selectedDate,
                    onSelectDate = { selectedDate = it },
                    colors = colors
                )
            },
            bottomBar = {
                ToolkitFixedButton(
                    colors = colors,
                    enabled = selectedDate.isNotEmpty(),
                    label = stringResource(R.string.btn_continue),
                    onClick = { redirectToInputNewExam(selectedDate) }
                )
            }
        )
    }
}