package com.vald3nir.myexams.presentation.features.insertExam.navigator

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vald3nir.myexams.presentation.features.insertExam.InsertExamScope
import com.vald3nir.myexams.presentation.features.insertExam.InsertExamViewModel
import com.vald3nir.myexams.presentation.features.insertExam.screen.NewExamScreen
import com.vald3nir.myexams.presentation.features.insertExam.screen.SelectDateScreen
import com.vald3nir.myexams.presentation.features.insertExam.screen.SelectLabScreen

@Composable
internal fun InsertExamScreenNavHost(activity: Activity) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<InsertExamViewModel>()
    val scope = InsertExamScope(activity = activity, viewModel = viewModel, navController = navController)
    NavHost(navController, startDestination = InsertExamScreenRoute.SelectLab) {
        composable<InsertExamScreenRoute.SelectLab> {
            scope.SelectLabScreen()
        }
        composable<InsertExamScreenRoute.SelectDate> {
            scope.SelectDateScreen()
        }
        composable<InsertExamScreenRoute.InsertExam> {
            scope.NewExamScreen()
        }
    }
}