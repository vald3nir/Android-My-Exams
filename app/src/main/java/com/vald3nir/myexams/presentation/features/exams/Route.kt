package com.vald3nir.myexams.presentation.features.exams

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vald3nir.myexams.presentation.features.exams.create.CreateExamScreen
import com.vald3nir.myexams.presentation.features.exams.details.ExamDetailsScreen
import com.vald3nir.myexams.presentation.features.exams.edit.EditExamScreen
import com.vald3nir.myexams.presentation.features.exams.home.HomeScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ------------------------------------------------------------------------------------------------------------
// Routers / Paths
// ------------------------------------------------------------------------------------------------------------
private sealed class Route {

    @Serializable
    @SerialName("home")
    data object HomeRoute : Route()

    @Serializable
    @SerialName("exam_details")
    data class ExamDetailsRoute(val examId: String) : Route()

    @Serializable
    @SerialName("edit_exam")
    data class EditExamRoute(val examId: String) : Route()

    @Serializable
    @SerialName("create_exam")
    data object CreateExamRoute : Route()
}

// ------------------------------------------------------------------------------------------------------------
// Extensions
// ------------------------------------------------------------------------------------------------------------

private fun NavController.navigateToExamDetails(examId: String) {
    this.navigate(Route.ExamDetailsRoute(examId))
}

private fun NavController.navigateToEditExam(examId: String) {
    this.navigate(Route.EditExamRoute(examId))
}

private fun NavController.navigateToCreateExam() {
    this.navigate(Route.CreateExamRoute)
}

// ------------------------------------------------------------------------------------------------------------
// Graph Routes
// ------------------------------------------------------------------------------------------------------------
@Composable
internal fun ExamsRouter(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.HomeRoute, modifier = modifier) {
        composable<Route.HomeRoute> {
            HomeScreen(
                onClickCreateExam = { navController.navigateToCreateExam() },
                onClickOpenExam = { examId -> examId?.let(navController::navigateToExamDetails) },
            )
        }

        composable<Route.ExamDetailsRoute> {
            ExamDetailsScreen(
                examId = it.getExamId(),
                onClickEditExam = { examId -> examId?.let(navController::navigateToEditExam) })
        }

        composable<Route.EditExamRoute> {
            EditExamScreen(examId = it.getExamId())
        }

        composable<Route.CreateExamRoute> {
            CreateExamScreen()
        }
    }
}

@Composable
private fun NavBackStackEntry.getExamId(): String = arguments?.getString("examId").orEmpty()