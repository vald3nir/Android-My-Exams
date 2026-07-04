package com.vald3nir.myexams.presentation.features.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vald3nir.myexams.R
import com.vald3nir.myexams.presentation.features.exams.ExamsRouter
import com.vald3nir.myexams.presentation.features.exams.evolution.EvolutionHistoryScreen
import com.vald3nir.myexams.presentation.features.profile.home.HomeProfileScreen
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitGradientBackground
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.navigation.ToolkitNavigationSuiteScaffold
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors

@Composable
internal fun AppScreen(viewModel: AppViewModel = hiltViewModel()) {
    val showEvolutionTab by viewModel.showEvolutionTabFlow.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val navController: NavHostController = rememberNavController()

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

    LaunchedEffect(Unit) {
        viewModel.navigateObserver().collect {
            navController.popBackStack()
        }
    }

    ToolkitBackground(modifier = Modifier) {
        ToolkitGradientBackground(gradientColors = LocalGradientColors.current) {
            ToolkitScaffold(snackBarHostState = snackBarHostState) {
                AppNavigation(
                    navController = navController,
                    showEvolutionTab = showEvolutionTab,
                )

            }
        }
    }
}

private enum class AppTab { Home, Evolution, Profile }

@Composable
private fun AppNavigation(
    navController: NavHostController,
    showEvolutionTab: Boolean,
) {
    var selectedTab: AppTab by remember { mutableStateOf(AppTab.Home) }

    LaunchedEffect(showEvolutionTab) {
        if (!showEvolutionTab && selectedTab == AppTab.Evolution) {
            selectedTab = AppTab.Home
        }
    }

    ToolkitNavigationSuiteScaffold(
        modifier = Modifier.fillMaxSize(),
        navigationSuiteItems = {
            item(
                selected = selectedTab == AppTab.Home,
                onClick = { selectedTab = AppTab.Home },
                icon = { ToolkitIcon(imageVector = ToolkitIconCatalog.Home) },
                label = { Text(stringResource(R.string.app_navigation_tab_home)) }
            )
            if (showEvolutionTab) {
                item(
                    selected = selectedTab == AppTab.Evolution,
                    onClick = { selectedTab = AppTab.Evolution },
                    icon = { ToolkitIcon(imageVector = ToolkitIconCatalog.BarChart) },
                    label = { Text(stringResource(R.string.app_navigation_tab_history)) },
                )
            }
            item(
                selected = selectedTab == AppTab.Profile,
                onClick = { selectedTab = AppTab.Profile },
                icon = { ToolkitIcon(imageVector = ToolkitIconCatalog.Person) },
                label = { Text(stringResource(R.string.app_navigation_tab_profile)) }
            )
        },
    ) {
        when (selectedTab) {
            AppTab.Home -> ExamsRouter(navController = navController)
            AppTab.Evolution -> EvolutionHistoryScreen()
            AppTab.Profile -> HomeProfileScreen()
        }
    }
}