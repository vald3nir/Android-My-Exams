package com.vald3nir.myexams.presentation.features.insertExam

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.repository.ExamsRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class InsertExamViewModel @Inject constructor(private val repository: ExamsRepository) : BaseViewModel() {

    fun insertNewExam(examDTO: ExamDTO?) {
        launchWithScope {
            repository.insertNewExam(examDTO)
            updateViewState(BaseScreenState.CallbackScreen())
        }
    }
}