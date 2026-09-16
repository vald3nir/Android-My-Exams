package com.vald3nir.myexams.presentation.features.profile.complete

import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CompleteProfileViewModel @Inject constructor(
    private val repository: AppRepository,
    parameters: BaseViewModelParameters,
) : BaseViewModel(parameters) {

    init {
        notifyState(BaseUiState.IdleState)
    }

    fun onCompleteProfile(profile: ProfileDTO) {
        runTaskUiState(
            action = { repository.completeProfile(birthday = profile.birthday, gender = profile.gender) },
            onSuccessEvent = { notifyState(BaseUiState.FinishState) }
        )
    }
}