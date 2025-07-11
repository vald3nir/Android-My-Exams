package com.vald3nir.toolkit.auth.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_INT_INVALID
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.auth.presentation.AuthScope
import com.vald3nir.toolkit.compose.components.base.BigSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitLinkButton
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.LocalAppColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import kotlinx.coroutines.launch

@Composable
internal fun AuthScope.HomeAuthScreen(imageLogo: Int) {
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
        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            HomeLoginScreenContent(
                imageLogo = imageLogo,
                colors = colors,
                snackBarHostState = snackBarHostState,
                onClickLoginWithGoogle = onClickLoginWithGoogle,
                onClickLoginWithEmailAndPassword = onClickRedirectToLogin,
                onTestClick = onClickUseFakeData
            )
        }
    }
}

@Composable
private fun HomeLoginScreenContent(
    imageLogo: Int,
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onClickLoginWithGoogle: () -> Unit = {},
    onClickLoginWithEmailAndPassword: (() -> Unit)? = null,
    onTestClick: () -> Unit = {},
) {
    ToolkitBaseContainer(
        snackBarHostState = snackBarHostState,
        backgroundColor = colors.backgroundColor,
        content = {
            Column {
                Header(imageLogo, colors)
                SectionLoginWithGoogle(
                    colors = colors,
                    onLoginClick = onClickLoginWithGoogle,
                )
                onClickLoginWithEmailAndPassword?.let {
                    SectionLoginWithEmailAndPassword(
                        colors = colors,
                        onClickLoginWithEmailAndPassword = it
                    )
                }
                SectionUseTestMode(
                    colors = colors,
                    onTestClick = onTestClick,
                )
            }
        }
    )
}

@Composable
private fun Header(imageLogo: Int, colors: ScreenColorSchema) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        DefaultSpaceHeight()
        if (imageLogo != AUTH_LIB_PARAM_INT_INVALID) {
            Image(
                painter = painterResource(id = imageLogo),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
        } else {
            ToolkitIcon(
                imageVector = Icons.Default.AccountCircle,
                tint = colors.iconTint,
                modifier = Modifier.size(48.dp)
            )
        }
        DefaultSpaceHeight()
        ToolkitText.Title(
            text = stringResource(R.string.auth),
            textColor = colors.textColor,
            modifier = Modifier.Companion.align(Alignment.CenterHorizontally)
        )
        BigSpaceHeight()
    }
}

@Composable
private fun SectionLoginWithGoogle(colors: ScreenColorSchema, onLoginClick: () -> Unit) {
    Column {
        ToolkitText.Label(
            text = stringResource(R.string.custom_login_title),
            textColor = colors.textColor,
            modifier = Modifier.Companion.align(Alignment.Start)
        )
        DefaultSpaceHeight()
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo_google),
                    tint = Color.Unspecified,
                    contentDescription = stringResource(R.string.btn_login),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ToolkitText.Label(
                    text = stringResource(R.string.btn_login),
                    textColor = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SectionLoginWithEmailAndPassword(colors: ScreenColorSchema, onClickLoginWithEmailAndPassword: () -> Unit) {
    Column {
        DefaultSpaceHeight()
        ToolkitText.Label(
            text = stringResource(R.string.custom_login_with_email_and_password),
            textColor = colors.textColor,
            modifier = Modifier.Companion.align(Alignment.Start)
        )
        DefaultSpaceHeight()
        Button(
            onClick = onClickLoginWithEmailAndPassword,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ToolkitIcon(
                    imageVector = ToolkitIcons.Email,
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ToolkitText.Label(
                    text = stringResource(R.string.btn_login),
                    textColor = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SectionUseTestMode(colors: ScreenColorSchema, onTestClick: () -> Unit) {
    Column {
        DefaultSpaceHeight()
        ToolkitText.Label(
            text = stringResource(R.string.custom_login_test_mode_description),
            textColor = colors.textColor,
            modifier = Modifier.Companion.align(Alignment.Start)
        )
        DefaultSpaceHeight()
        ToolkitLinkButton(
            onClick = onTestClick,
            btnLabel = stringResource(R.string.btn_test),
            labelColor = colors.linkColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    HomeLoginScreenContent(
        colors = DefaultThemeColors().lightColors,
        onClickLoginWithGoogle = {},
        onClickLoginWithEmailAndPassword = {},
        imageLogo = AUTH_LIB_PARAM_INT_INVALID,
        onTestClick = {},
    )
}