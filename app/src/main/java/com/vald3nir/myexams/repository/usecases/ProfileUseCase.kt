package com.vald3nir.myexams.repository.usecases

import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.datasource.ProfileDataSource
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ProfileUseCase @Inject constructor(
    private val analytics: AnalyticsHelper,
    private val dataSource: ProfileDataSource
) {

    private val profileStateFlow = MutableStateFlow<ProfileDTO?>(null)

    suspend fun loadProfile(): ProfileDTO? {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        val profile = dataSource.loadProfile(email)
        analytics.onLog("loadProfile email = $email, profile = $profile")
        return profile
    }

    private suspend fun updateProfileStateFlow() {
        profileStateFlow.value = loadProfile()
    }

    fun loadProfileFlow(): Flow<ProfileDTO?> {
        return profileStateFlow.onStart {
            if (profileStateFlow.value == null) {
                updateProfileStateFlow()
            }
        }
    }

    suspend fun createProfile(authenticatedUser: AuthenticatedUserDTO?) {
        val email = authenticatedUser?.email.orEmpty()
        analytics.onLog("createProfile email=$email, authenticatedUser=$authenticatedUser")
        if (dataSource.loadProfile(email) == null) {
            authenticatedUser?.toProfile()?.let {
                analytics.onLog("insertProfile $it")
                dataSource.insertProfile(it)
            }
        } else {
            analytics.onLog("profile created!!!")
        }
        updateProfileStateFlow()
    }

    suspend fun updateProfile(profile: ProfileDTO) {
        analytics.onLog("updateProfile $profile")
        dataSource.updateProfile(profile)
        updateProfileStateFlow()
    }

    suspend fun completeProfile(birthday: String?, gender: String?) {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        val profile = (dataSource.loadProfile(email) ?: ProfileDTO()).copy(birthday = birthday, gender = gender)
        analytics.onLog("completeProfile email=$email, birthday=$birthday, gender=$gender profile=$profile")
        dataSource.updateProfile(profile)
        updateProfileStateFlow()
    }
}

private fun AuthenticatedUserDTO.toProfile() = ProfileDTO(
    name = this.name,
    email = this.email,
    photoUrl = this.photoUrl,
)