package com.vald3nir.myexams.presentation.features.boot

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.myexams.presentation.features.home.startExamsHomeActivity
import com.vald3nir.myexams.presentation.features.profile.redirectCreateProfileActivity
import com.vald3nir.myexams.repository.ImportDataRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BootViewModel @Inject constructor(private val repository: ImportDataRepository) : BaseViewModel() {

    fun checkUserLoggedAndDownloadDatabase(context: Context, onRedirectToAuth: () -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (!FirebaseAuthenticator.isUserLogged()) {
                    onRedirectToAuth()
                    return@launch
                }
                downloadDatabase(context)
            }
        }
    }

    fun downloadDatabase(context: Context) {
        viewModelScope.launch {
            repository.importLaboratoriesFromServer(context)
            repository.importExamsFromServer()
            repository.importProfileFromServer()
            checkRedirects(context)
        }
    }

    fun useTrial(context: Context) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.importLaboratoriesFromLocal(context = context)
                checkRedirects(context)
            }
        }
    }

    private fun checkRedirects(context: Context) {
        viewModelScope.launch {
            kotlin.runCatching {
                if (repository.hasProfileEdited()) {
                    context.startExamsHomeActivity()
                } else {
                    context.redirectCreateProfileActivity()
                }
            }
        }
    }
}