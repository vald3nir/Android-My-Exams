package com.vald3nir.myexams.presentation.features.profile.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.appMyExamsTheme
import com.vald3nir.myexams.presentation.components.ComponentEditProfile
import com.vald3nir.myexams.presentation.features.profile.ProfileScope
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.loadAppColorSchema
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import kotlinx.coroutines.launch

@Composable
internal fun ProfileScope.CreateProfileScreen() {
    val context = LocalContext.current
    val colors = context.loadAppColorSchema()
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

    AppTheme {
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            CreateProfileScreenContent(colors, snackBarHostState, onSaveProfile = eventCreateProfile)
        }
    }
}

@Composable
private fun CreateProfileScreenContent(
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onSaveProfile: (birthday: String?, gender: String?) -> Unit,
) {
    var birthday by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }

    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = stringResource(R.string.edit_profile_title),
                colors = colors,
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
        },
        bottomBar = {
            ToolkitFixedButton(
                colors = colors,
                enabled = birthday.isNotEmpty() && selectedGender.isNotEmpty(),
                label = stringResource(R.string.btn_continue),
                onClick = {
                    onSaveProfile(birthday, selectedGender)
                }
            )
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    CreateProfileScreenContent(
        colors = appMyExamsTheme(),
        onSaveProfile = { _, _ -> },
    )
}