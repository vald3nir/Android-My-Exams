package com.vald3nir.myexams.presentation.features.onboarding

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator.observeUserLogged
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class OnboardingViewModel @Inject constructor(
    private val repository: AppRepository,
    parameters: BaseViewModelParameters,
) : BaseViewModel(parameters) {

    val uiModel: StateFlow<OnboardingUiModel> by lazy {
        combine(
            observeUserLogged(),
            repository.loadProfileFlow()
        ) { isUserLogged, currentProfile ->
            OnboardingUiModel(
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
            initialValue = OnboardingUiModel()
        )
    }
}