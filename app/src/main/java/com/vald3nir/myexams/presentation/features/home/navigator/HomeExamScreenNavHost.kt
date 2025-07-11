package com.vald3nir.myexams.presentation.features.home.navigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vald3nir.myexams.presentation.features.home.ExamsHomeScope
import com.vald3nir.myexams.presentation.features.home.ExamsHomeViewModel
import com.vald3nir.myexams.presentation.features.home.screens.EditExamDateScreen
import com.vald3nir.myexams.presentation.features.home.screens.EditExamLabScreen
import com.vald3nir.myexams.presentation.features.home.screens.EditExamScreen
import com.vald3nir.myexams.presentation.features.home.screens.HomeDetailScreen
import com.vald3nir.myexams.presentation.features.home.screens.HomeExamsScreen

@Composable
internal fun HomeExamScreenNavHost(viewModelStoreOwner: ViewModelStoreOwner) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<ExamsHomeViewModel>(viewModelStoreOwner)
    val scope = ExamsHomeScope(viewModel = viewModel, navController = navController)
    NavHost(navController, startDestination = HomeExamScreenRoute.Home) {
        composable<HomeExamScreenRoute.Home> { scope.HomeExamsScreen() }
        composable<HomeExamScreenRoute.ExamDetail> { backStackEntry ->
            scope.HomeDetailScreen(backStackEntry.toRoute<HomeExamScreenRoute.ExamDetail>().idExam)
        }
        composable<HomeExamScreenRoute.EditLab> { backStackEntry ->
            scope.EditExamLabScreen(backStackEntry.toRoute<HomeExamScreenRoute.EditLab>().idExam)
        }
        composable<HomeExamScreenRoute.EditDate> { backStackEntry ->
            scope.EditExamDateScreen(backStackEntry.toRoute<HomeExamScreenRoute.EditDate>().idExam)
        }
        composable<HomeExamScreenRoute.EditExam> { backStackEntry ->
            scope.EditExamScreen(backStackEntry.toRoute<HomeExamScreenRoute.EditExam>().idExam)
        }
    }
}