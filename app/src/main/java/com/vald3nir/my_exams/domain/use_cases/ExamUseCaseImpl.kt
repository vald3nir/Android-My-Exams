package com.vald3nir.my_exams.domain.use_cases

import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.data.repository.ExamRepository
import javax.inject.Inject

class ExamUseCaseImpl @Inject constructor(private val repository: ExamRepository) : ExamUseCase {

    override suspend fun downloadExams(
        key: String,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        onShowLoading?.invoke(true)
        repository.downloadExams(key, onShowLoading, onSuccess, onError)
    }

    override suspend fun loadExamsLocal(
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.loadExamsLocal(onShowLoading, onSuccess, onError)
    }

    override suspend fun hasExams() = repository.hasExams()

    override suspend fun deleteExamsLocal(
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.deleteExamsLocal(onSuccess, onError)
    }

    override suspend fun deleteExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.deleteExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun updateExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.updateExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun insertNewExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.insertNewExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun insertExamsLocal(
        exams: List<ExamDTO>,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.insertExamsLocal(exams, onShowLoading, onSuccess, onError)
    }
}