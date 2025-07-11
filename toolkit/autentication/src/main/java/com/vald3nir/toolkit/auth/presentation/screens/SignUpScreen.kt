package com.vald3nir.toolkit.auth.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.auth.presentation.AuthScope
import com.vald3nir.toolkit.auth.presentation.enableBtnContinue
import com.vald3nir.toolkit.auth.validations.validateEmail
import com.vald3nir.toolkit.auth.validations.validatePassword
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitInputPasswordComponent
import com.vald3nir.toolkit.compose.components.inputs.ToolkitInputTextComponent
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.LocalAppColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import kotlinx.coroutines.launch

@Composable
internal fun AuthScope.SignUpScreen() {
    AppTheme {
        val colors = LocalAppColors.current
        var isLoading by remember { mutableStateOf(false) }
        val snackBarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        CollectUiState(
            onShowMessage = { message ->
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(message)
                }
            },
            onLoading = { isLoading = it },
        )
        SignUpScreenContent(
            colors = colors,
            showLoading = isLoading,
            snackBarHostState = snackBarHostState,
            onClickSignUp = onClickSignUp,
            onBackPressed = { onBackPressed() }
        )
    }
}

@Composable
private fun SignUpScreenContent(
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    showLoading: Boolean = false,
    colors: ScreenColorSchema,
    onBackPressed: () -> Unit = {},
    onClickSignUp: (email: String, password: String) -> Unit = { _, _ -> },
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorEmailMessage by remember { mutableStateOf<String?>(null) }
    var errorPasswordMessage by remember { mutableStateOf<String?>(null) }
    val context: Context = LocalContext.current

    ToolkitBaseContainer(
        snackBarHostState = snackBarHostState,
        backgroundColor = colors.backgroundColor,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = stringResource(R.string.signup_title_screen),
                colors = colors,
                onBackClick = onBackPressed,
            )
        },
        content = {
            Column {

                ToolkitInputTextComponent(
                    modifier = Modifier.fillMaxWidth(),
                    inputValue = email,
                    errorValue = errorEmailMessage,
                    placeholder = stringResource(R.string.input_email),
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        email = it
                        errorEmailMessage = context.validateEmail(email)
                    },
                    label = stringResource(R.string.email),
                    startIcon = ToolkitIcons.Email,
                    singleLine = true,
                    colors = colors
                )

                DefaultSpaceHeight()
                ToolkitInputPasswordComponent(
                    modifier = Modifier.fillMaxWidth(),
                    inputValue = password,
                    placeholder = stringResource(R.string.input_password),
                    onValueChange = {
                        password = it
                    },
                    label = stringResource(R.string.password),
                    colors = colors
                )

                DefaultSpaceHeight()
                ToolkitInputPasswordComponent(
                    modifier = Modifier.fillMaxWidth(),
                    inputValue = confirmPassword,
                    errorValue = errorPasswordMessage,
                    placeholder = stringResource(R.string.confirm_your_password),
                    onValueChange = {
                        confirmPassword = it
                        errorPasswordMessage = context.validatePassword(password, confirmPassword)
                    },
                    label = stringResource(R.string.confirm_password),
                    colors = colors
                )
            }
        },
        bottomBar = {
            ToolkitFixedButton(
                enabled = enableBtnContinue(email = email, password = password, errorPasswordMessage = errorPasswordMessage, errorEmailMessage = errorEmailMessage),
                showLoading = showLoading,
                label = stringResource(R.string.signup),
                colors = colors,
                onClick = { onClickSignUp(email, password) }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignUpScreenContent(colors = DefaultThemeColors().darkColors)
}