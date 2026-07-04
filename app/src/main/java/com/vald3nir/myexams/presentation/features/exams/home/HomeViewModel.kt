package com.vald3nir.myexams.presentation.features.exams.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.HomeScreenDTO
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

    val screenDataFlow: StateFlow<HomeScreenDTO?> by lazy {
        combine(
            repository.listExamsHomeScreen(),
            hasInternetConnection,
            searchQuery
        ) { exams, hasConnection, query ->
            val normalizedQuery = query.trim().lowercase()
            if (normalizedQuery.isEmpty()) {
                HomeScreenDTO(hasInternetConnection = hasConnection, exams = exams)
            } else {
                HomeScreenDTO(hasInternetConnection = hasConnection, exams = exams.filter { it.filter(normalizedQuery) })
            }
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach {
            if (!it.hasInternetConnection) {
                notifyState(BaseUiState.OffLineState)
                return@onEach
            }
            if (it.exams.isEmpty()) {
                notifyState(BaseUiState.EmptySate)
                return@onEach
            }
            notifyState(BaseUiState.ShowState())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    }
}