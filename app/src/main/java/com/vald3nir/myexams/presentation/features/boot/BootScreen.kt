package com.vald3nir.myexams.presentation.features.boot

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.myexams.R
import com.vald3nir.myexams.di.AppPrimaryColorInt
import com.vald3nir.toolkit.auth.AUTH_LIB_PARAM_LOGIN_RESPONSE
import com.vald3nir.toolkit.auth.AuthLibLoginResponseType
import com.vald3nir.toolkit.auth.presentation.buildAuthActivityIntent
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen

@Composable
internal fun BootScreen(activity: Activity) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<BootViewModel>()

    val activityResultLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val response: String? = result.data?.getStringExtra(AUTH_LIB_PARAM_LOGIN_RESPONSE)
            when (response) {
                AuthLibLoginResponseType.SUCCESS.name -> viewModel.downloadDatabase(context)
                AuthLibLoginResponseType.USE_TRIAL.name -> viewModel.useTrial(context)
                else -> activity.finish()
            }
        } else {
            activity.finish()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkUserLoggedAndDownloadDatabase(
            context = context,
            onRedirectToAuth = {
                val intent = context.buildAuthActivityIntent(
                    serverClientId = BuildConfig.SERVER_CLIENT_ID,
                    imageLogo = R.drawable.ic_logo,
                    primaryColor = AppPrimaryColorInt.toInt()
                )
                activityResultLauncher.launch(intent)
            }
        )
    }

    ToolkitBaseLoadingScreen()
}