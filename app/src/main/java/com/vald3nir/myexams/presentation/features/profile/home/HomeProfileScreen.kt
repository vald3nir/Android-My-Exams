package com.vald3nir.myexams.presentation.features.profile.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.enums.ProfileEditField
import com.vald3nir.myexams.presentation.components.AppPreview
import com.vald3nir.myexams.presentation.components.EditProfileNameDialog
import com.vald3nir.myexams.presentation.components.ProfileGenderDialog
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitOutlinedButton
import com.vald3nir.toolkit.designsystem.components.dialogs.toolkitDatePickerDialog
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.lists.ToolkitFieldCard
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitColumn
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitProfileContent

@Composable
internal fun HomeProfileScreen(viewModel: HomeProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiModel by viewModel.uiModel.collectAsState()
    when (uiState) {
        is BaseUiState.LoadingState -> ToolkitLoadingFullscreen()
        else -> ScreenContent(uiModel = uiModel)
    }
}

@Composable
private fun ScreenContent(uiModel: HomeProfileUiModel) {
    val context = LocalContext.current
    var editingField by remember { mutableStateOf<ProfileEditField?>(null) }
    val datePickerDialog = remember(context) {
        toolkitDatePickerDialog(
            context = context,
            onSelect = { uiModel.onChangeBirthDate(birthday = it) }
        )
    }
    ToolkitColumn(
        modifier = Modifier.padding(ToolkitSpacingMd),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ToolkitProfileContent(
            userName = uiModel.profile.name,
            userEmail = uiModel.profile.email,
            isAuthenticated = true,
            userImageUrl = uiModel.profile.photoUrl
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_name),
            value = uiModel.profile.name.orEmpty(),
            onEdit = { editingField = ProfileEditField.Name }
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_email),
            value = uiModel.profile.email.orEmpty(),
            onEdit = null
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_birthdate),
            value = uiModel.profile.birthday.orEmpty(),
            onEdit = { datePickerDialog.show() }
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_gender),
            value = uiModel.profile.gender.orEmpty(),
            onEdit = { editingField = ProfileEditField.Gender }
        )

        ToolkitOutlinedButton(
            text = stringResource(R.string.profile_change_user),
            leadingIcon = ToolkitIconCatalog.Logout,
            onClick = { uiModel.logout() }
        )
    }

    when (editingField) {
        ProfileEditField.Name -> EditProfileNameDialog(
            currentName = uiModel.profile.name.orEmpty(),
            onCancel = { editingField = null },
            onConfirm = {
                uiModel.onChangeName(name = it)
                editingField = null
            }
        )
        ProfileEditField.Gender -> ProfileGenderDialog(
            currentGender = uiModel.profile.gender.orEmpty(),
            onConfirm = {
                uiModel.onChangeGender(gender = it)
                editingField = null
            },
            onCancel = { editingField = null }
        )
        else -> Unit
    }
}


@AppPreview
@Composable
private fun Preview(@PreviewParameter(HomeProfileProvider::class) uiModel: HomeProfileUiModel) {
    ToolkitPreviewContainer {
        ScreenContent(uiModel = uiModel)
    }
}