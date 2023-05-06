package com.vald3nir.my_exams.data.repository

import com.vald3nir.core.repository.BaseRepository
import com.vald3nir.core.repository.requestCallBack
import com.vald3nir.firebase_helpers.FirebaseDB
import com.vald3nir.firebase_helpers.extensions.parseStringListToObjects
import com.vald3nir.my_exams.data.database.daos.ExamDAO
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.domain.mappers.asDTO
import com.vald3nir.my_exams.domain.mappers.asEntity
import com.vald3nir.my_exams.domain.mappers.toMap
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val examDAO: ExamDAO
) : ExamRepository, BaseRepository() {

    override suspend fun downloadExams(
        key: String,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        FirebaseDB.readList(
            path = key,
            onShowLoading = onShowLoading,
            onError = onError,
            onSuccess = {
                val exams: List<ExamDTO> = it.parseStringListToObjects()
                onSuccess.invoke(exams)
            }
        )
    }

    override suspend fun loadExamsLocal(
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        request(
            call = suspend { examDAO.getExams() },
            callback = requestCallBack(
                onShowLoading = onShowLoading,
                onSuccess = { onSuccess.invoke(it?.asDTO().orEmpty()) },
                onError = onError,
            )
        )
    }

    override suspend fun hasExams() = examDAO.size() > 0

    override suspend fun deleteExamsLocal(
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            examDAO.clear()
            onSuccess.invoke()
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    override suspend fun updateExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            examDAO.updateExam(exam.asEntity())
            uploadExams(key, onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    override suspend fun deleteExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            examDAO.deleteExamById(exam.id)
            uploadExams(key, onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    override suspend fun insertNewExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            examDAO.insertExam(exam.asEntity())
            uploadExams(key, onShowLoading, onSuccess, onError)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    private fun uploadExams(
        key: String,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            val exams = examDAO.getExams().asDTO()
            FirebaseDB.insertOrUpdate(
                path = key,
                data = exams.toMap(),
                onShowLoading = onShowLoading,
                onError = onError,
                onSuccess = { onSuccess.invoke() }
            )
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    override suspend fun insertExamsLocal(
        exams: List<ExamDTO>,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        try {
            examDAO.clear()
            examDAO.insertExams(exams.asEntity())
            onSuccess.invoke()
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }
}