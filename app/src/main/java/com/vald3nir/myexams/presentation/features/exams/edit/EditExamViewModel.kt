package com.vald3nir.myexams.presentation.features.exams.edit

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.CreateExamScreenDTO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.enums.CreateExamStep
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditExamViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val appRepository: AppRepository,
) : BaseViewModel(parameters) {
    private val examFlow = MutableStateFlow(ExamDTO())
    private val stepFlow = MutableStateFlow(CreateExamStep.Date)

    val screenDataFlow: StateFlow<CreateExamScreenDTO> = combine(
        examFlow,
        appRepository.loadLabsFlow(),
        appRepository.loadTopLabsFlow(),
    ) { exam, labs, topLabs ->
        CreateExamScreenDTO(
            exam = exam,
            labs = labs,
            topLabs = topLabs,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CreateExamScreenDTO(),
    )

    val currentStepFlow: StateFlow<CreateExamStep> = stepFlow.asStateFlow()

    fun loadExam(examId: String) {
        viewModelScope.launch {
            val exam = appRepository.getExamById(examId).first()
            if (exam == null) {
                navigateBack()
                return@launch
            }
            examFlow.value = exam
        }
    }

    fun onExamChanged(exam: ExamDTO) {
        examFlow.value = exam
    }

    fun goToNextStep() {
        stepFlow.value = when (stepFlow.value) {
            CreateExamStep.Pdf,
            CreateExamStep.Date -> CreateExamStep.Lab
            CreateExamStep.Lab -> CreateExamStep.Fields
            CreateExamStep.Fields -> CreateExamStep.Fields
        }
    }

    fun goToPreviousStep() {
        when (stepFlow.value) {
            CreateExamStep.Pdf,
            CreateExamStep.Date -> navigateBack()
            CreateExamStep.Lab -> stepFlow.value = CreateExamStep.Date
            CreateExamStep.Fields -> stepFlow.value = CreateExamStep.Lab
        }
    }

    fun saveExam() {
        viewModelScope.launch {
            appRepository.updateExam(exam = examFlow.value)
            navigateBack()
        }
    }
}
