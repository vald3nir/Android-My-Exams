package com.vald3nir.myexams.presentation.features.exams.details

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamDetailsScreenDTO
import com.vald3nir.myexams.domain.validations.validateExam
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ExamDetailsViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val repository: AppRepository,
) : BaseViewModel(parameters) {

    private val examFlow = MutableStateFlow<ExamDTO?>(null)

    val screenDataFlow: StateFlow<ExamDetailsScreenDTO> by lazy {
        combine(
            examFlow, repository.loadProfileFlow()
        ) { exam, profile ->
            ExamDetailsScreenDTO(exam = exam ?: ExamDTO(), validation = validateExam(exam, profile))
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            notifyState(BaseUiState.ShowState())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExamDetailsScreenDTO()
        )
    }

    fun loadExam(examId: String) {
        viewModelScope.launch {
            val exam = repository.getExamById(examId).first()
            if (exam == null) {
                navigateBack()
                return@launch
            }
            examFlow.value = exam
        }
    }
}