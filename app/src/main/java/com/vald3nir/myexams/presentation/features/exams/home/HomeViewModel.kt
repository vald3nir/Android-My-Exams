package com.vald3nir.myexams.presentation.features.exams.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val repository: AppRepository
) : BaseViewModel(parameters) {

    val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    val screenDataFlow: StateFlow<HomeUiModel?> by lazy {
        combine(
            repository.listExamsFlow(),
            repository.loadProfileFlow(),
            hasInternetConnection,
            searchQuery
        ) { exams, profile, hasConnection, query ->
            bindHomeUIModel(
                exams = exams.orEmpty(),
                profile = profile,
                hasInternetConnection = hasConnection,
                filterText = query
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            if (!it.hasInternetConnection) {
                notifyState(BaseUiState.OffLineState)
                return@onEach
            }
            if (it.items.isEmpty()) {
                notifyState(BaseUiState.EmptyState)
                return@onEach
            }
            notifyState(BaseUiState.ShowState())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    }

    private fun bindHomeUIModel(exams: List<ExamDTO>, profile: ProfileDTO?, hasInternetConnection: Boolean, filterText: String): HomeUiModel {
        val normalizedQuery = filterText.trim().lowercase()
        var items: List<ItemHomeUiModel> = exams.map { exam ->
            ItemHomeUiModel(
                idExam = exam.id,
                date = exam.date,
                lab = exam.lab,
                alerts = validateExam(exam, profile).alertsSize
            )
        }
        if (normalizedQuery.isNotEmpty()) {
            items = items.filter { it.filter(normalizedQuery) }
        }
        return HomeUiModel(
            items = items,
            hasInternetConnection = hasInternetConnection,
        )
    }

    private fun ItemHomeUiModel.filter(query: String) = date?.contains(query, ignoreCase = true) == true || lab?.contains(query, ignoreCase = true) == true
}