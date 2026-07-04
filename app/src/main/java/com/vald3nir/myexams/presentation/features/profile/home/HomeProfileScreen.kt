package com.vald3nir.myexams.presentation.features.profile.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ProfileDTO
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
    val profile by viewModel.profileStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    when (uiState) {
        is BaseUiState.LoadingState -> ToolkitLoadingFullscreen()
        else -> ScreenContent(
            profile = profile,
            onUpdateProfile = viewModel::updateProfile,
            logout = viewModel::logout
        )
    }
}

@Composable
private fun ScreenContent(profile: ProfileDTO, onUpdateProfile: (ProfileDTO) -> Unit = {}, logout: () -> Unit = {}) {
    val context = LocalContext.current
    var editingField by remember { mutableStateOf<ProfileEditField?>(null) }
    val datePickerDialog = remember(context) {
        toolkitDatePickerDialog(
            context = context,
            onSelect = { onUpdateProfile(profile.copy(birthday = it)) }
        )
    }
    ToolkitColumn(
        modifier = Modifier.padding(ToolkitSpacingMd),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ToolkitProfileContent(
            userName = profile.name,
            userEmail = profile.email,
            isAuthenticated = true,
            userImageUrl = profile.photoUrl
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_name),
            value = profile.name.orEmpty(),
            onEdit = { editingField = ProfileEditField.Name }
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_email),
            value = profile.email.orEmpty(),
            onEdit = null
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_birthdate),
            value = profile.birthday.orEmpty(),
            onEdit = { datePickerDialog.show() }
        )

        ToolkitFieldCard(
            label = stringResource(R.string.profile_screen_gender),
            value = profile.gender.orEmpty(),
            onEdit = { editingField = ProfileEditField.Gender }
        )

        ToolkitOutlinedButton(
            text = stringResource(R.string.profile_change_user),
            leadingIcon = ToolkitIconCatalog.Logout,
            onClick = logout
        )
    }

    when (editingField) {
        ProfileEditField.Name -> EditProfileNameDialog(
            currentName = profile.name.orEmpty(),
            onCancel = { editingField = null },
            onConfirm = {
                onUpdateProfile(profile.copy(name = it))
                editingField = null
            }
        )

        ProfileEditField.Gender -> ProfileGenderDialog(
            currentGender = profile.gender.orEmpty(),
            onConfirm = {
                onUpdateProfile(profile.copy(gender = it))
                editingField = null
            },
            onCancel = { editingField = null }
        )

        else -> Unit
    }
}

private enum class ProfileEditField { Name, Gender, }


@AppPreview
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ScreenContent(
            profile = ProfileDTO(
                name = "Vald3nir",
                email = "vald3nir@gmail.com",
                birthday = "15/08/1991",
                gender = "Feminino",
                photoUrl = "https://avatars.githubusercontent.com/u/12345678?v=4"
            )
        )
    }
}