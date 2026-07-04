package com.vald3nir.myexams.presentation.features.profile.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import com.vald3nir.toolkit.core.baseclasses.treatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeProfileViewModel @Inject constructor(
    parameters: BaseViewModelParameters,
    private val authenticatedUserRepository: AuthenticatedUserRepository,
    private val repository: AppRepository,
) : BaseViewModel(parameters) {

    private val profileFlow = MutableStateFlow(ProfileDTO())
    val profileStateFlow: StateFlow<ProfileDTO> = profileFlow.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            notifyState(BaseUiState.LoadingState(true))
            runCatching {
                repository.loadProfile()
            }.onSuccess { profile ->
                profileFlow.value = profile ?: ProfileDTO()
                notifyState(BaseUiState.ShowState())
            }.onFailure { error ->
                notifyState(BaseUiState.ShowState())
                error.treatMessage { notifyUiMessage(it) }
            }
        }
    }

    fun updateProfile(profile: ProfileDTO) {
        viewModelScope.launch {
            if (profile.birthdateIsValid().not()) {
                notifyUiMessage(messageId = R.string.profile_screen_birthdate_invalid)
                return@launch
            }
            runCatching {
                repository.updateProfile(profile)
            }.onSuccess {
                profileFlow.value = profile
            }.onFailure { error ->
                error.treatMessage { notifyUiMessage(it) }
            }
        }
    }

    fun logout() {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState())
                authenticatedUserRepository.logout()
            },
            onSuccessEvent = {
                navigateBack()
            }
        )
    }
}
