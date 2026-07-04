package com.vald3nir.myexams.repository.di.impls

import android.content.Context
import com.vald3nir.myexams.presentation.main.startOnboardingActivity
import com.vald3nir.myexams.repository.usecases.ExamsUseCase
import com.vald3nir.myexams.repository.usecases.ProfileUseCase
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

internal class AuthenticatedUserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val analytics: AnalyticsHelper,
    private val examsUseCase: ExamsUseCase,
    private val profileUseCase: ProfileUseCase,
    private val supabaseClient: SupabaseClient,
) : AuthenticatedUserRepository {

    override suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?) {
        analytics.onLog("update_authenticated_user")
        profileUseCase.createProfile(authenticatedUser)
    }

    override fun loadAuthenticatedUser(): Flow<AuthenticatedUserDTO> = flow { emit(FirebaseAuthenticator.getFirebaseUser() ?: AuthenticatedUserDTO()) }

    override suspend fun logout() {
        analytics.onLog("logout")
        examsUseCase.clearStateFlow()
        FirebaseAuthenticator.disconnect()
        context.startOnboardingActivity()
    }

    override suspend fun onAuthenticateWithGoogle(googleIdToken: String, uuid: UUID) = supabaseClient.auth.signInWith(IDToken) {
        analytics.onLog("onAuthenticateWithGoogle")
        idToken = googleIdToken
        provider = Google
        nonce = uuid.toString()
    }
}