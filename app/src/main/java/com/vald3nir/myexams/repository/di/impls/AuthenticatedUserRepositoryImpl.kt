package com.vald3nir.myexams.repository.di.impls

import com.vald3nir.myexams.repository.usecases.ProfileUseCase
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

internal class AuthenticatedUserRepositoryImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val supabaseClient: SupabaseClient,
) : AuthenticatedUserRepository {

    override suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?) = profileUseCase.createProfile(authenticatedUser)

    override fun loadAuthenticatedUser(): Flow<AuthenticatedUserDTO> = flow { emit(FirebaseAuthenticator.getFirebaseUser() ?: AuthenticatedUserDTO()) }

    override suspend fun logout() = FirebaseAuthenticator.disconnect()

    override suspend fun onAuthenticateWithGoogle(googleIdToken: String, uuid: UUID) = supabaseClient.auth.signInWith(IDToken) {
        idToken = googleIdToken
        provider = Google
        nonce = uuid.toString()
    }
}