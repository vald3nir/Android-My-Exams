package com.vald3nir.myexams.presentation.features.home

import android.content.Context
import androidx.navigation.NavController
import com.vald3nir.myexams.presentation.features.home.navigator.HomeExamScreenRoute
import com.vald3nir.myexams.presentation.features.insertExam.startInsertExamActivity
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

internal data class ExamsHomeScope(
    val viewModel: ExamsHomeViewModel,
    val navController: NavController,
) : BaseScreenScope(viewModel, navController) {

    val updateSearchQueryEvent = viewModel::updateSearchQuery

    fun redirectToExamDetail(examID: Long?) {
        navController.navigate(HomeExamScreenRoute.ExamDetail(examID))
    }

    fun redirectToCreateNewExam(context: Context) {
        context.startInsertExamActivity()
    }

    fun deleteExam(idExam: Long?) {
        idExam?.let { viewModel.deleteExam(it) }
    }

    fun redirectToEditExam(idExam: Long?) {
        viewModel.emitNavigationEvent(HomeExamScreenRoute.EditExam(idExam))
    }

    fun redirectToEditExamLab(idExam: Long?) {
        viewModel.emitNavigationEvent(HomeExamScreenRoute.EditLab(idExam))
    }

    fun redirectToEditExamDate(idExam: Long?) {
        viewModel.emitNavigationEvent(HomeExamScreenRoute.EditDate(idExam))
    }

    fun userPhotoUrl() = viewModel.getUserLogged()?.photoUrl

}