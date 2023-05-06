package com.vald3nir.my_exams.domain.use_cases

import com.vald3nir.my_exams.data.dto.ExamDTO

interface ExamUseCase {

    suspend fun downloadExams(
        key: String,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun loadExamsLocal(
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun hasExams(): Boolean

    suspend fun deleteExamsLocal(
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun deleteExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun updateExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun insertNewExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun insertExamsLocal(
        exams: List<ExamDTO>,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )
}