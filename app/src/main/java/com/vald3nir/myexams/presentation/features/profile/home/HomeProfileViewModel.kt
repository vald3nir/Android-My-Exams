package com.vald3nir.myexams.presentation.features.profile.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeProfileViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val authenticatedUserRepository: AuthenticatedUserRepository,
    private val repository: AppRepository,
) : BaseViewModel(parameters), HomeProfileUiModelListener {

    val uiModel: StateFlow<HomeProfileUiModel> = repository.loadProfileFlow()
        .map { HomeProfileUiModel(profile = it ?: ProfileDTO(), listener = this) }
        .onStart { notifyState(BaseUiState.LoadingState()) }
        .onEach { data ->
            notifyState(BaseUiState.ShowState(data))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = HomeProfileUiModel(),
        )

    override fun onEditProfile(profile: ProfileDTO) {
        runTaskUiState(
            action = { repository.updateProfile(profile) },
            onSuccessEvent = { notifyState(BaseUiState.ShowState()) }
        )
    }

    override fun logout() {
        runTaskUiState(
            action = { authenticatedUserRepository.logout() },
            onSuccessEvent = { navigateBack() }
        )
    }
}
