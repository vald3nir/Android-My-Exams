package com.vald3nir.myexams.repository.usecases

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamsHomeScreenDTO
import com.vald3nir.myexams.domain.validations.validateExam
import com.vald3nir.myexams.repository.datasource.ExamsDataSource
import com.vald3nir.myexams.repository.datasource.ProfileDataSource
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ExamsUseCase @Inject constructor(private val examsDataSource: ExamsDataSource, private val profileDataSource: ProfileDataSource) {
    private val refreshFlow = MutableStateFlow(0)

    fun listExams(): Flow<List<ExamDTO>> = refreshFlow.mapLatest {
        examsDataSource.loadExams()
    }

    fun listExamsHomeScreen(): Flow<List<ExamsHomeScreenDTO>> = refreshFlow.mapLatest {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        val exams = examsDataSource.loadExams()
        val profile = profileDataSource.loadProfile(email)
        exams.map { exam ->
            ExamsHomeScreenDTO(
                id = exam.id,
                date = exam.date,
                lab = exam.lab,
                hasAlerts = validateExam(exam, profile).hasAlerts(),
            )
        }
    }

    suspend fun insertExam(exam: ExamDTO) {
        examsDataSource.insertExam(exam)
        notifyRefresh()
    }

    suspend fun updateExam(exam: ExamDTO) {
        examsDataSource.updateExam(exam)
        notifyRefresh()
    }

    fun getExamById(examId: String) = flow { emit(examsDataSource.loadExamById(examId)) }
    suspend fun parseExamFromPdf(pdfPath: String): ExamDTO = withContext(Dispatchers.IO) {
        ExamPdfParser.parse(pdfPath = pdfPath)
    }

    private fun notifyRefresh() {
        refreshFlow.value += 1
    }
}