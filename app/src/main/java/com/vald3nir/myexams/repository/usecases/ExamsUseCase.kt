package com.vald3nir.myexams.repository.usecases

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.repository.datasource.ExamsDataSource
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ExamsUseCase @Inject constructor(private val examsDataSource: ExamsDataSource) {

    private fun userEmail() = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()

    private val stateFlow = MutableStateFlow<List<ExamDTO>?>(null)

    private suspend fun updateStateFlow() {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        stateFlow.value = examsDataSource.loadExams(email)
    }

    fun examsStateFlow(): Flow<List<ExamDTO>?> = stateFlow.onStart {
        if (stateFlow.value == null) updateStateFlow()
    }

    suspend fun insertExam(exam: ExamDTO) {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        examsDataSource.insertExam(exam.copy(owner = email))
        updateStateFlow()
    }

    suspend fun updateExam(exam: ExamDTO) {
        examsDataSource.updateExam(exam)
        updateStateFlow()
    }

    fun getExamById(examId: String) = flow { emit(examsDataSource.loadExamById(email = userEmail(), examId = examId)) }

    suspend fun parseExamFromPdf(pdfPath: String): ExamDTO = withContext(Dispatchers.IO) {
        ExamPdfParser.parse(pdfPath = pdfPath)
    }
}