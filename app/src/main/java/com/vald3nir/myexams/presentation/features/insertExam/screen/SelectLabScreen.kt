package com.vald3nir.myexams.presentation.features.insertExam.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.presentation.components.ComponentSelectLaboratory
import com.vald3nir.myexams.presentation.features.insertExam.InsertExamScope
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer

@Composable
internal fun InsertExamScope.SelectLabScreen() {
    AppTheme {

        val colors = LocalContext.current.loadAppColorSchema()
        var labName by remember { mutableStateOf("") }

        ToolkitBaseContainer(
            backgroundColor = colors.backgroundColor,
            topBarContent = {
                ToolkitGenericToolbarComponent(
                    title = stringResource(R.string.select_laboratory),
                    colors = colors,
                    onBackClick = { redirectToHome() }
                )
            },
            content = {
                DefaultSpaceHeight()
                ToolkitText.Label(text = stringResource(R.string.select_laboratory_description), textColor = colors.textColor)
                DefaultSpaceHeight()
                ComponentSelectLaboratory(
                    colors = colors,
                    inputValue = labName,
                    useTransparentBackend = true,
                    onSelected = {
                        labName = it.name.orEmpty()
                    }
                )
            },
            bottomBar = {
                ToolkitFixedButton(
                    colors = colors,
                    enabled = labName.isNotEmpty(),
                    label = stringResource(R.string.btn_continue),
                    onClick = { redirectToSelectDate(labName) }
                )
            }
        )
    }
}