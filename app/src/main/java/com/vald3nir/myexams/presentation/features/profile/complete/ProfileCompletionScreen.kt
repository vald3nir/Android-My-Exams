package com.vald3nir.myexams.presentation.features.profile.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.genderEnumList
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.toolkit.core.utils.extensions.finishAffinity
import com.vald3nir.toolkit.core.utils.extensions.getElapsedTimeText
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXxl
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitLinkButton
import com.vald3nir.toolkit.designsystem.components.dialogs.toolkitDatePickerDialog
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroup
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroupType
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun ProfileCompletionScreen(
    profile: ProfileDTO,
    onUpdateProfile: (ProfileDTO) -> Unit = {},
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val localProfileState = remember { MutableStateFlow(profile) }
    val localProfile by localProfileState.collectAsStateWithLifecycle()
    LaunchedEffect(profile) { localProfileState.value = profile }

    val datePickerDialog = remember(context) {
        toolkitDatePickerDialog(
            context = context,
            onSelect = { birthdate -> localProfileState.value = localProfile.copy(birthday = birthdate) }
        )
    }
    val birthdateIsEdited = localProfile.birthday.isNullOrEmpty().not()

    ToolkitScaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.complete_profile_title),
                extraIcon = ToolkitIconCatalog.Close,
                onClickExtraIcon = { context.finishAffinity() }
            )
        },
        bottomBar = {
            if (localProfile.needCompleteProfile().not()) {
                ToolkitBaseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingXxl),
                    leadingIcon = ToolkitIconCatalog.Save,
                    onClick = { onUpdateProfile(localProfile) },
                    text = stringResource(R.string.btn_update)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(ToolkitSpacingMd)
        ) {
            ToolkitText(
                text = stringResource(R.string.complete_profile_description),
                style = ToolkitTextStyle.TitleSmall
            )

            ToolkitDivider(modifier = Modifier.padding(vertical = ToolkitSpacingXl))
            ToolkitText(
                text = if (birthdateIsEdited) {
                    stringResource(R.string.selected_date_, localProfile.birthday.orEmpty())
                } else {
                    stringResource(R.string.profile_screen_no_date_selected)
                },
                style = ToolkitTextStyle.TitleSmall
            )
            if (localProfile.birthdateIsValid()) {
                ToolkitText(
                    text = stringResource(
                        R.string.profile_screen_age,
                        localProfile.birthday.orEmpty().getElapsedTimeText()
                    ),
                    style = ToolkitTextStyle.TitleSmall
                )
            } else if (birthdateIsEdited) {
                ToolkitSpaceHeight()
                ToolkitText(
                    text = stringResource(R.string.profile_screen_birthdate_invalid),
                    style = ToolkitTextStyle.BodySmall,
                    textColor = MaterialTheme.colorScheme.error
                )
            }
            ToolkitSpaceHeight()
            ToolkitLinkButton(
                onClick = { datePickerDialog.show() },
                label = stringResource(R.string.select_date)
            )

            ToolkitDivider(modifier = Modifier.padding(vertical = ToolkitSpacingXl))
            ToolkitText(
                text = stringResource(R.string.complete_profile_select_biological_sex),
                style = ToolkitTextStyle.TitleSmall
            )
            Box(
                modifier = Modifier.size(500.dp, 50.dp), contentAlignment = Alignment.Center
            ) {
                ToolkitRadioButtonGroup(
                    selectedValue = localProfile.gender.orEmpty(),
                    onItemSelected = { gender -> localProfileState.value = localProfile.copy(gender = gender) },
                    items = genderEnumList(),
                    viewType = ToolkitRadioButtonGroupType.GRID,
                )
            }

            ToolkitDivider(modifier = Modifier.padding(vertical = ToolkitSpacingXl))
        }
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ProfileCompletionScreen(
            profile = ProfileDTO(
                birthday = "15/08/1991",
                gender = "Feminino",
            )
        )
    }
}