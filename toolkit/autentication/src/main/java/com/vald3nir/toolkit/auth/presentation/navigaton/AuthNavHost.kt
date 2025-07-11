package com.vald3nir.toolkit.auth.presentation.navigaton

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vald3nir.toolkit.auth.presentation.AuthScope
import com.vald3nir.toolkit.auth.presentation.AuthViewModel
import com.vald3nir.toolkit.auth.presentation.screens.HomeAuthScreen
import com.vald3nir.toolkit.auth.presentation.screens.LoginScreen
import com.vald3nir.toolkit.auth.presentation.screens.SignUpScreen
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity

@Composable
fun BaseActivity.AuthNavHost(serverClientId: String, imageLogo: Int) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<AuthViewModel>()
    viewModel.serverClientId = serverClientId

    NavHost(navController, startDestination = AuthScreenRoute.HomeAuth) {

        composable<AuthScreenRoute.HomeAuth> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = viewModel,
                navController = navController
            ).HomeAuthScreen(imageLogo)
        }

        composable<AuthScreenRoute.Login> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = viewModel,
                navController = navController
            ).LoginScreen()
        }

        composable<AuthScreenRoute.SignUp> {
            AuthScope(
                activity = this@AuthNavHost,
                viewModel = viewModel,
                navController = navController
            ).SignUpScreen()
        }
    }
}