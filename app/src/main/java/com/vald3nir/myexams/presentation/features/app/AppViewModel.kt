package com.vald3nir.myexams.presentation.features.app

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.AppScreenUiState
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator.observeUserLogged
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class AppViewModel @Inject constructor(
    private val repository: AppRepository,
    parameters: BaseViewModelParameters,
) : BaseViewModel(parameters) {

    private val profileFlow = repository.loadProfileFlow()

    val screenDataFlow: StateFlow<AppScreenUiState> by lazy {
        combine(
            observeUserLogged(),
            profileFlow
        ) { isUserLogged, currentProfile ->
            AppScreenUiState(
                isUserLogged = isUserLogged,
                profile = currentProfile
            )
        }.onStart {
            notifyState(BaseUiState.LoadingState(true))
        }.onEach { data ->
            notifyState(BaseUiState.ShowState(data.redirect))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppScreenUiState()
        )
    }

    val showEvolutionTabFlow: StateFlow<Boolean> by lazy {
        repository
            .listExamsFlow()
            .map { exams -> exams.orEmpty().size > 2 }
            .catch { emit(false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )
    }

    fun updateProfile(profile: ProfileDTO) {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState(true))
                repository.completeProfile(
                    birthday = profile.birthday,
                    gender = profile.gender
                )
            }
        )
    }
}