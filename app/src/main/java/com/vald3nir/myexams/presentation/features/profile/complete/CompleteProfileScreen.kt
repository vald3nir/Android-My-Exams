package com.vald3nir.myexams.presentation.features.profile.complete

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.genderEnumList
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.AppTopBar
import com.vald3nir.myexams.presentation.main.startMainActivity
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.finishAffinity
import com.vald3nir.toolkit.core.utils.extensions.getElapsedTimeText
import com.vald3nir.toolkit.core.utils.extensions.isValidBirthdate
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
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
internal fun CompleteProfileScreen(viewModel: CompleteProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is BaseUiState.LoadingState -> ToolkitLoadingFullscreen()
        is BaseUiState.FinishState -> LocalContext.current.startMainActivity()
        else -> ScreenContent(onCompleteProfile = { profile ->
            viewModel.onCompleteProfile(profile)
        })
    }
}

@Composable
private fun ScreenContent(onCompleteProfile: (ProfileDTO) -> Unit = {}) {
    val context = LocalContext.current
    var profile by remember { mutableStateOf(ProfileDTO()) }
    val datePickerDialog = remember(context) {
        toolkitDatePickerDialog(
            context = context,
            onSelect = { birthday ->
                profile = profile.copy(birthday = birthday)
            }
        )
    }

    ToolkitScaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.complete_profile_title),
                extraIcon = ToolkitIconCatalog.Close,
                onClickExtraIcon = { context.finishAffinity() }
            )
        },
        bottomBar = {
            if (profile.birthday.isValidBirthdate() && !profile.gender.isNullOrBlank()) {
                ToolkitBaseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingXxl),
                    leadingIcon = ToolkitIconCatalog.Save,
                    onClick = { onCompleteProfile(profile) },
                    text = stringResource(R.string.btn_update)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(ToolkitSpacingMd)
        ) {
            ToolkitText(
                text = stringResource(R.string.complete_profile_description),
                style = ToolkitTextStyle.TitleSmall
            )

            ToolkitDivider(modifier = Modifier.padding(vertical = ToolkitSpacingXl))

            val birthday = profile.birthday
            ToolkitText(
                text = if (birthday != null) {
                    stringResource(R.string.selected_date_, birthday)
                } else {
                    stringResource(R.string.profile_screen_no_date_selected)
                },
                style = ToolkitTextStyle.TitleSmall
            )

            if (birthday.isValidBirthdate()) {
                ToolkitText(
                    text = stringResource(
                        R.string.profile_screen_age,
                        birthday.orEmpty().getElapsedTimeText()
                    ),
                    style = ToolkitTextStyle.TitleSmall
                )
            } else if (birthday != null) {
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

            ToolkitRadioButtonGroup(
                selectedValue = profile.gender.orEmpty(),
                onItemSelected = { gender ->
                    profile = profile.copy(gender = gender)
                },
                items = genderEnumList(),
                viewType = ToolkitRadioButtonGroupType.GRID,
            )

            ToolkitDivider(modifier = Modifier.padding(vertical = ToolkitSpacingXl))
        }
    }
}

@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ScreenContent()
    }
}