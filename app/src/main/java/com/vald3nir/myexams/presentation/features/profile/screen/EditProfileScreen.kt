package com.vald3nir.myexams.presentation.features.profile.screen

import androidx.compose.foundation.clickable
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.presentation.components.ComponentEditProfile
import com.vald3nir.myexams.presentation.features.profile.ProfileScope
import com.vald3nir.toolkit.auth.presentation.components.ComponentLogoutUserDialog
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import com.vald3nir.toolkit.helpers.utils.orFalse
import kotlinx.coroutines.launch

@Composable
internal fun ProfileScope.EditProfileScreen() {
    val context = LocalContext.current
    val colors = context.loadAppColorSchema()
    val profile: ProfileDTO? by viewModel.currentProfile.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }


    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onLoading = { isLoading = it },
        onCallbackScreen = { event ->
            context.checkRedirectEvents(event)
        }
    )

    viewModel.loadProfile()


    AppTheme {
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            profile?.EditProfileScreenContent(
                colors = colors,
                snackBarHostState = snackBarHostState,
                onBackPressed = { eventRedirectHome(context) },
                onClickLogout = { eventChangeUser() },
                onUpdateProfile = eventUpdateProfile,
            )
        }
    }
}

@Composable
private fun ProfileDTO.EditProfileScreenContent(
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onBackPressed: () -> Unit = {},
    onClickLogout: () -> Unit = {},
    onUpdateProfile: (ProfileDTO) -> Unit,
) {
    var birthday by remember { mutableStateOf(birthday.orEmpty()) }
    var selectedGender by remember { mutableStateOf(gender.orEmpty()) }
    var showDialog by remember { mutableStateOf(false) }

    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = stringResource(R.string.edit_profile_title),
                colors = colors,
                onBackClick = { onBackPressed() },
                genericContent = {
                    ToolkitText.Link(
                        text = stringResource(R.string.logout),
                        textColor = colors.textOverlayColor,
                        modifier = Modifier.clickable { showDialog = true },
                    )
                }
            )
        },
        content = {
            ComponentEditProfile(
                colors = colors,
                currentBirthDay = birthday,
                currentGender = selectedGender,
                onChangeBirthday = { birthday = it },
                onChangeGender = { selectedGender = it }
            )
            if (showDialog) {
                ComponentLogoutUserDialog(
                    colors = colors,
                    onDismissRequest = { showDialog = false },
                    onConfirm = onClickLogout
                )
            }
        },
        bottomBar = {
            ToolkitFixedButton(
                colors = colors,
                enabled = readyToSave().orFalse(),
                label = stringResource(R.string.update),
                onClick = {
                    onUpdateProfile(
                        this.copy(
                            birthday = birthday,
                            gender = selectedGender,
                        )
                    )
                }
            )
        }
    )
}