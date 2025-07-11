package com.vald3nir.myexams.presentation.features.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.presentation.features.ScreenEvents
import com.vald3nir.myexams.repository.ExamsRepository
import com.vald3nir.myexams.repository.ProfileRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ExamsHomeViewModel @Inject constructor(private val examsRepository: ExamsRepository, private val profileRepository: ProfileRepository) : BaseViewModel() {


    fun getUserLogged() = FirebaseAuthenticator.getFirebaseUser()


    private val _currentExam = MutableStateFlow<ExamDTO?>(null)
    val currentExam: StateFlow<ExamDTO?> = _currentExam.asStateFlow()

    private val _currentProfile = MutableStateFlow<ProfileDTO?>(null)
    val currentProfile: StateFlow<ProfileDTO?> = _currentProfile.asStateFlow()

    fun loadExam(examID: Long?) {
        launchWithScope {
            if (examID != null) {
                examsRepository.getExam(examID).collect {
                    _currentExam.value = it
                }
            }
        }
    }


    fun loadProfile() {
        launchWithScope {
            profileRepository.getProfile().collect {
                updateViewState(BaseScreenState.Loading(false))
                _currentProfile.value = it
            }
        }
    }

    fun updateExam(exam: ExamDTO?) {
        launchWithScope {
            examsRepository.updateExam(exam)
            updateViewState(BaseScreenState.CallbackScreen(ScreenEvents.ExamUpdated))
        }
    }

    fun deleteExam(examId: Long) {
        launchWithScope {
            examsRepository.delete(examId)
            updateViewState(BaseScreenState.CallbackScreen(ScreenEvents.ExamDeleted))
        }
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val filteredList = searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            examsRepository.getAll()
        } else {
            examsRepository.searchExams(query)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}