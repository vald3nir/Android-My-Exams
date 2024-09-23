package com.vald3nir.my_exams.data.repository

import com.vald3nir.my_exams.data.dto.ExamDTO

interface ExamRepository {

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
        key: String?,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)? = null,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    )

    suspend fun insertExams(
        exams: List<ExamDTO>,
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