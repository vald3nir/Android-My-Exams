package com.vald3nir.myexams.presentation.features.profile

import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.presentation.features.ScreenEvents
import com.vald3nir.myexams.repository.ExamsRepository
import com.vald3nir.myexams.repository.ProfileRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(private val examsRepository: ExamsRepository, private val profileRepository: ProfileRepository) : BaseViewModel() {

    private val _currentProfile = MutableStateFlow<ProfileDTO?>(null)
    val currentProfile: StateFlow<ProfileDTO?> = _currentProfile.asStateFlow()

    fun loadProfile() {
        launchWithScope {
            profileRepository.getProfile().collect {
                updateViewState(BaseScreenState.Loading(false))
                _currentProfile.value = it
            }
        }
    }

    fun userLogout() {
        launchWithScope {
            examsRepository.clean()
            profileRepository.clean()
            FirebaseAuthenticator.disconnect()
            updateViewState(BaseScreenState.CallbackScreen(ScreenEvents.UserLogout))
        }
    }

    fun updateProfile(profile: ProfileDTO) {
        launchWithScope {
            profileRepository.saveProfile(profile)
            updateViewState(BaseScreenState.CallbackScreen(ScreenEvents.GoHome))
        }
    }

    fun createProfile(birthday: String?, gender: String?) {
        launchWithScope {
            val userLogger = FirebaseAuthenticator.getFirebaseUser()
            val profile = ProfileDTO(
                email = userLogger?.email,
                userImage = userLogger?.photoUrl,
                birthday = birthday,
                gender = gender
            )
            updateProfile(profile)
        }
    }

}