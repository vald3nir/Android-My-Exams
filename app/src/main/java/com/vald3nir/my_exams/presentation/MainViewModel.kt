package com.vald3nir.my_exams.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vald3nir.auth.google.GoogleSign
import com.vald3nir.auth.google.GoogleUserDTO
import com.vald3nir.core.presentation.CoreFragment
import com.vald3nir.core.presentation.CoreViewModel
import com.vald3nir.firebase_helpers.parseEmailToKey
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.domain.use_cases.ExamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val examUseCase: ExamUseCase) : CoreViewModel() {


    // ---------------------------------------------------------------------------------------------
    // Authentication
    // ---------------------------------------------------------------------------------------------

    var googleUserDTO: GoogleUserDTO? = null

    fun logoutTextButton(context: Context): String {
        return context.getString(if (googleUserDTO == null) R.string.login else R.string.logout)
    }

    fun logout(fragment: CoreFragment, onSuccess: () -> Unit) {
        viewModelScope.launch {
            GoogleSign.googleLogout(fragment.activity)
            examUseCase.deleteExamsLocal(
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }

    fun loadUserGoogle(fragment: CoreFragment, onError: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            GoogleSign.loadUserGoogle(fragment.activity, onError = onError, onSuccess = {
                googleUserDTO = it
                onSuccess.invoke()
            })
        }
    }

    fun runLiveDemo(onSuccess: () -> Unit) {
        viewModelScope.launch {
            examUseCase.runLiveDemo(
                onShowLoading = showLoading,
                onSuccess = onSuccess,
                onError = onError
            )
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Update DataBase on Start
    // ---------------------------------------------------------------------------------------------

    fun checkDatabase(onSuccess: () -> Unit) {
        googleUserDTO?.email?.let { email ->
            viewModelScope.launch {
                if (examUseCase.hasExams()) {
                    onSuccess.invoke()
                } else {
                    examUseCase.downloadExams(
                        key = email.parseEmailToKey(),
                        onShowLoading = showLoading,
                        onSuccess = { updateDatabase(it, onSuccess) },
                        onError = onError
                    )
                }
            }
        }
    }

    private fun updateDatabase(exams: List<ExamDTO>, onSuccess: () -> Unit) {
        viewModelScope.launch {
            examUseCase.insertExamsLocal(exams, showLoading, onSuccess, onError)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // List My Exams
    // ---------------------------------------------------------------------------------------------

    private val _exams = MutableLiveData<List<ExamDTO>>()
    val exams: LiveData<List<ExamDTO>> = _exams

    fun loadExams() {
        viewModelScope.launch {
            examUseCase.loadExamsLocal(
                onShowLoading = showLoading, onSuccess = { _exams.value = it }, onError = onError
            )
        }
    }


    // ---------------------------------------------------------------------------------------------
    // Update Exam
    // ---------------------------------------------------------------------------------------------

    var currentExamDTO: ExamDTO? = null

    fun updateExam(examDTO: ExamDTO, onSuccess: () -> Unit) {
        googleUserDTO?.email.let { email ->
            viewModelScope.launch {
                examUseCase.updateExam(
                    key = email?.parseEmailToKey(),
                    exam = examDTO,
                    onShowLoading = showLoading,
                    onSuccess = onSuccess,
                    onError = onError
                )
            }
        }
    }

    fun deleteExam(examDTO: ExamDTO, onSuccess: () -> Unit) {
        googleUserDTO?.email?.let { email ->
            viewModelScope.launch {
                examUseCase.deleteExam(
                    key = email.parseEmailToKey(),
                    exam = examDTO,
                    onShowLoading = showLoading,
                    onSuccess = onSuccess,
                    onError = onError
                )
            }
        }
    }


    // ---------------------------------------------------------------------------------------------
    // Create new Exam
    // ---------------------------------------------------------------------------------------------

    fun insertExam(examDTO: ExamDTO, context: Context, onSuccess: () -> Unit) {
        googleUserDTO?.email?.let { email ->
            viewModelScope.launch {
                examUseCase.insertNewExam(
                    key = email.parseEmailToKey(),
                    exam = examDTO,
                    onShowLoading = showLoading,
                    onError = onError,
                    onSuccess = onSuccess
                )
            }
        }
    }
}