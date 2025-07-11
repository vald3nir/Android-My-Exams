package com.vald3nir.myexams.repository

import com.vald3nir.myexams.db.dao.ExamDAO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.mappers.toDTO
import com.vald3nir.myexams.domain.mappers.toModel
import com.vald3nir.toolkit.helpers.utils.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface ExamsRepository {
    suspend fun clean()
    suspend fun delete(examId: Long)
    fun getAll(): Flow<List<ExamDTO>>
    fun getExam(examID: Long): Flow<ExamDTO>
    fun searchExams(query: String): Flow<List<ExamDTO>>
    suspend fun updateExam(exam: ExamDTO?)
    suspend fun insertNewExam(exam: ExamDTO?)
}

internal class ExamsRepositoryImpl @Inject constructor(private val dao: ExamDAO) : ExamsRepository {

    override suspend fun clean() {
        dao.clean()
    }

    override suspend fun delete(examId: Long) {
        dao.delete(examId)
    }

    override fun getAll(): Flow<List<ExamDTO>> {
        return dao.getAllExams().map { it.map { entity -> entity.toDTO() } }
    }

    override fun searchExams(query: String): Flow<List<ExamDTO>> {
        return dao.searchExams(query).map { it.map { entity -> entity.toDTO() } }
    }

    override fun getExam(examID: Long): Flow<ExamDTO> {
        return dao.getExam(examID).map { it.toDTO() }
    }

    override suspend fun updateExam(exam: ExamDTO?) {
        exam?.let {
            dao.upsert(it.toModel().copy(id = exam.modelId.orZero()))
            exportExams()
        }
    }

    override suspend fun insertNewExam(exam: ExamDTO?) {
        exam?.let {
            dao.upsert(it.toModel())
            exportExams()
        }
    }

    private suspend fun exportExams() {
        FirebaseUseCase.exportExams(dao.listAllExams())
    }
}