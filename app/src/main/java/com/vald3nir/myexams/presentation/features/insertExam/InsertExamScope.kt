package com.vald3nir.myexams.presentation.features.insertExam

import android.app.Activity
import androidx.navigation.NavController
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.presentation.features.insertExam.navigator.InsertExamScreenRoute
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

internal data class InsertExamScope(
    val activity: Activity,
    val viewModel: InsertExamViewModel,
    val navController: NavController
) : BaseScreenScope(navController = navController, viewModel = viewModel) {

    var newExam: ExamDTO = ExamDTO()

    fun redirectToSelectDate(labName: String) {
        newExam.lab = labName
        navController.navigate(InsertExamScreenRoute.SelectDate)
    }

    fun redirectToInputNewExam(selectedDate: String) {
        newExam.date = selectedDate
        navController.navigate(InsertExamScreenRoute.InsertExam)
    }

    fun insertNewExam(exam: ExamDTO?) {
        viewModel.insertNewExam(exam)
    }

    fun redirectToHome(){
        activity.finish()
    }

}