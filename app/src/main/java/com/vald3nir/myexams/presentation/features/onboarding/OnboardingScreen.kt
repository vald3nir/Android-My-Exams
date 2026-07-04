package com.vald3nir.myexams.presentation.features.onboarding

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.enums.AppScreenRedirect
import com.vald3nir.myexams.presentation.features.profile.complete.ProfileCompletionScreen
import com.vald3nir.myexams.presentation.main.startMainActivity
import com.vald3nir.toolkit.auth.presentation.AuthScreen
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitGradientBackground
import com.vald3nir.toolkit.designsystem.templates.ToolkitLoadingFullscreen
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors

@Composable
internal fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel()) {
    val screenData by viewModel.screenDataFlow.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.messageObserver().collect { message ->
            if (message.isNotEmpty()) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    ToolkitBackground(modifier = Modifier) {
        ToolkitGradientBackground(gradientColors = LocalGradientColors.current) {
            ToolkitScaffold(snackBarHostState = snackBarHostState) {

                if (uiState is BaseUiState.LoadingState) {
                    ToolkitLoadingFullscreen()

                } else {
                    when (screenData.redirect) {

                        AppScreenRedirect.LOADING -> {
                            ToolkitLoadingFullscreen()
                        }

                        AppScreenRedirect.AUTH -> {
                            AuthScreen(
                                appPrivacyPolicyURL = BuildConfig.APP_PRIVACY_POLICY_URL,
                                appTermsUseLink = BuildConfig.APP_TERMS_USE_URL,
                                webGoogleClientID = BuildConfig.WEB_GOOGLE_CLIENT_ID,
                            )
                        }

                        AppScreenRedirect.COMPLETE_PROFILE -> {
                            ProfileCompletionScreen(
                                profile = screenData.profile ?: ProfileDTO(),
                                onUpdateProfile = viewModel::updateProfile,
                            )
                        }

                        AppScreenRedirect.HOME -> {
                            LocalContext.current.startMainActivity()
                        }
                    }
                }
            }
        }
    }
}