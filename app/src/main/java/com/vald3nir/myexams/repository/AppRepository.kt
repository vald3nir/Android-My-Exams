package com.vald3nir.myexams.repository

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ExamsHomeScreenDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import kotlinx.coroutines.flow.Flow

internal interface AppRepository {
    suspend fun loadProfile(): ProfileDTO?
    fun loadProfileFlow(): Flow<ProfileDTO?>
    suspend fun updateProfile(profile: ProfileDTO)
    suspend fun completeProfile(birthday: String?, gender: String?)
    fun listExams(): Flow<List<ExamDTO>>
    fun listExamsHomeScreen(): Flow<List<ExamsHomeScreenDTO>>
    fun loadLabsFlow(): Flow<List<String>>
    fun loadTopLabsFlow(): Flow<List<String>>
    fun getExamById(examId: String): Flow<ExamDTO?>
    suspend fun parseExamFromPdf(pdfPath: String): ExamDTO
    suspend fun insertExam(exam: ExamDTO)
    suspend fun updateExam(exam: ExamDTO)
}