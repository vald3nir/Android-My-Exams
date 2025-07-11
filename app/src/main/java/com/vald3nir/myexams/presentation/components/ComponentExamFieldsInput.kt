package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.mock.examMock1
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitColumn
import com.vald3nir.toolkit.compose.components.inputs.ToolkitIntegerInputField
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.utils.formatString

@Composable
internal fun ComponentExamFieldsInput(
    currentExam: ExamDTO?,
    colors: ScreenColorSchema,
    onUpdateExam: (ExamDTO?) -> Unit = {},
) {
    var totalCholesterolLabel by remember { mutableStateOf("") }
    var hdlLabel by remember { mutableStateOf("") }
    var notHDLLabel by remember { mutableStateOf("") }
    var ldlLabel by remember { mutableStateOf("") }
    var triglyceridesLabel by remember { mutableStateOf("") }
    var uricAcidLabel by remember { mutableStateOf("") }

    LaunchedEffect(currentExam) {
        totalCholesterolLabel = currentExam?.totalCholesterol.formatString()
        hdlLabel = currentExam?.HDL.formatString()
        notHDLLabel = currentExam?.notHDL.formatString()
        ldlLabel = currentExam?.LDL.formatString()
        triglyceridesLabel = currentExam?.triglycerides.formatString()
        uricAcidLabel = currentExam?.uricAcid.formatString()
    }

    fun updateExam() {
        onUpdateExam(
            currentExam?.copy(
                totalCholesterol = totalCholesterolLabel.toIntOrNull(),
                HDL = hdlLabel.toIntOrNull(),
                notHDL = notHDLLabel.toIntOrNull(),
                LDL = ldlLabel.toIntOrNull(),
                triglycerides = triglyceridesLabel.toIntOrNull(),
                uricAcid = uricAcidLabel.toFloatOrNull()
            )
        )
    }

    ToolkitColumn(flagNoPadding = true) {

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.total_cholesterol),
            inputValue = totalCholesterolLabel,
            useTransparentBackend = true,
            onValueChange = {
                totalCholesterolLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.hdl_d),
            inputValue = hdlLabel,
            useTransparentBackend = true,
            onValueChange = {
                hdlLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.no_hdl),
            inputValue = notHDLLabel,
            useTransparentBackend = true,
            onValueChange = {
                notHDLLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.ldl),
            inputValue = ldlLabel,
            useTransparentBackend = true,
            onValueChange = {
                ldlLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.triglycerides),
            inputValue = triglyceridesLabel,
            useTransparentBackend = true,
            onValueChange = {
                triglyceridesLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()

        ToolkitIntegerInputField(
            colors = colors,
            label = stringResource(R.string.uric_acid),
            inputValue = uricAcidLabel,
            useTransparentBackend = true,
            isDecimal = true,
            onValueChange = {
                uricAcidLabel = it
                updateExam()
            }
        )
        DefaultSpaceHeight()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentExamFieldsInput(
        currentExam = examMock1,
        colors = appMyExamsTheme(),
    )
}