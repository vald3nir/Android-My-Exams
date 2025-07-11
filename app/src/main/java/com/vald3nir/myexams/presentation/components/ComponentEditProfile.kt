package com.vald3nir.myexams.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.enums.genderEnumList
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitColumn
import com.vald3nir.toolkit.compose.components.base.ToolkitRadioButtonGroup
import com.vald3nir.toolkit.compose.components.base.ToolkitRadioButtonGroupType
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.calendar.buildToolkitDatePickerDialog
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ComponentEditProfile(
    colors: ScreenColorSchema,
    currentBirthDay: String,
    currentGender: String,
    onChangeBirthday: (String) -> Unit,
    onChangeGender: (String) -> Unit,
) {

    val context = LocalContext.current
    val datePickerDialog = buildToolkitDatePickerDialog(context = context, onSelect = {
        onChangeBirthday(it)
    })

    ToolkitText.Label(text = stringResource(R.string.edit_profile_description), textColor = colors.textColor)
    DefaultSpaceHeight()

    ToolkitCard(backgroundColor = colors.overlayColor, onClick = { datePickerDialog.show() }) {
        ToolkitColumn {
            ToolkitRow(flagNoPadding = true) {
                ToolkitText.Label(
                    text = stringResource(R.string.birthday_date_),
                    textColor = colors.textOverlayColor
                )
                ToolkitText.Label(
                    text = currentBirthDay,
                    textColor = colors.textOverlayColor
                )
            }
            DefaultSpaceHeight()
            ToolkitText.Link(
                text = stringResource(R.string.select_date),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                textColor = colors.linkOverlayColor
            )
        }
    }

    DefaultSpaceHeight()

    ToolkitCard(backgroundColor = colors.overlayColor) {
        ToolkitColumn {
            ToolkitText.Label(
                text = stringResource(R.string.gender_),
                textColor = colors.textOverlayColor
            )
            ToolkitRadioButtonGroup(
                items = genderEnumList(),
                selectedValue = currentGender,
                viewType = ToolkitRadioButtonGroupType.GRID,
                colors = colors,
                onItemSelected = { onChangeGender(it) }
            )
        }
    }
}