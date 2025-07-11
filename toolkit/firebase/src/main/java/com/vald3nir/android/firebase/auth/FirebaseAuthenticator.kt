package com.vald3nir.android.firebase.auth

import android.app.Activity
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vald3nir.android.firebase.utils.notifyLog

data class FirebaseUser(
    val id: String,
    val name: String?,
    val email: String?,
    val photoUrl: String?
)

object FirebaseAuthenticator {

    fun disconnect() {
        Firebase.auth.signOut()
    }

    fun getFirebaseUser() = Firebase.auth.currentUser?.let {
        FirebaseUser(
            id = it.uid,
            name = it.displayName,
            email = it.email,
            photoUrl = it.photoUrl?.toString()
        )
    }

    fun isUserLogged(): Boolean = Firebase.auth.currentUser != null

    suspend fun loginWithGoogle(activity: Activity?, serverClientId: String, onLoginSuccess: () -> Unit, onLoginError: (Throwable?) -> Unit) {
        if (activity == null) {
            onLoginError(Exception("Erro ao realizar login"))
            return
        }
        val credentialManager = CredentialManager.create(activity)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(serverClientId)
            .setFilterByAuthorizedAccounts(false) // Try false to allow all accounts
            .build()
        val request = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()
        runCatching {
            val result = credentialManager.getCredential(context = activity, request = request)
            val idToken = GoogleIdTokenCredential.createFrom(result.credential.data).idToken
            Firebase.auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null)).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onLoginSuccess()
                } else {
                    task.exception?.notifyLog()
                    onLoginError(Exception("Erro ao realizar login, verifique suas credenciais"))
                }

            }
        }.onFailure {
            it.notifyLog()
            onLoginError(Exception("Erro ao realizar login, verifique suas credenciais"))
        }
    }

    fun loginWithEmailAndPassword(activity: Activity?, email: String, password: String, onLoginSuccess: () -> Unit, onLoginError: (Throwable?) -> Unit) {
        if (activity == null) {
            onLoginError(Exception("Erro ao realizar login"))
            return
        }
        runCatching {
            Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onLoginSuccess()
                } else {
                    task.exception?.notifyLog()
                    onLoginError(Exception("Erro ao realizar login, verifique suas credenciais"))
                }

            }
        }.onFailure {
            it.notifyLog()
            onLoginError(Exception("Erro ao realizar login, verifique suas credenciais"))
        }
    }

    fun createUserWithEmailAndPassword(activity: Activity?, email: String, password: String, onCreateUserSuccess: () -> Unit, onLoginError: (Throwable?) -> Unit) {
        if (activity == null) {
            onLoginError(Exception("Erro ao criar usuário"))
            return
        }
        runCatching {
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onCreateUserSuccess()
                } else {
                    task.exception?.printStackTrace()
                    when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> onLoginError(Exception("Senha fraca, digite uma senha com mais de 5 caracteres"))
                        is FirebaseAuthUserCollisionException -> onLoginError(Exception("Já existe uma conta com esse email"))
                        else -> onLoginError(Exception("Erro ao criar usuário"))
                    }
                }
            }
        }.onFailure {
            it.printStackTrace()
            onLoginError(Exception("Erro ao criar usuário"))
        }
    }

    fun sendEmailVerification(): String {
        Firebase.auth.currentUser?.sendEmailVerification()
        return "Confirmação de email enviada, verifique sua caixa de entrada"
    }
}