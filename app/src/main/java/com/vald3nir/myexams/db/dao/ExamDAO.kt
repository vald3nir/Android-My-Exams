package com.vald3nir.myexams.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.vald3nir.myexams.db.model.ExamModel
import com.vald3nir.myexams.db.model.ExamModel.Companion.EXAMS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ExamDAO {
    @Query("DELETE FROM $EXAMS_TABLE_NAME")
    suspend fun clean()

    @Query("UPDATE $EXAMS_TABLE_NAME SET enable = 0 WHERE id = :examId")
    suspend fun delete(examId: Long)

    @Upsert
    suspend fun upsert(entity: ExamModel): Long

    @Upsert
    suspend fun upsertAll(entities: List<ExamModel>): List<Long>

    @Transaction
    suspend fun clearAndInsert(entities: List<ExamModel>) {
        clean()
        upsertAll(entities)
    }

    @Query("SELECT * FROM $EXAMS_TABLE_NAME WHERE enable = 1 ORDER BY date DESC")
    fun getAllExams(): Flow<List<ExamModel>>

    @Query("SELECT * FROM $EXAMS_TABLE_NAME WHERE id = :examID")
    fun getExam(examID: Long): Flow<ExamModel>

    @Query("SELECT * FROM $EXAMS_TABLE_NAME ORDER BY date DESC")
    suspend fun listAllExams(): List<ExamModel>

    @Query(
        """
            SELECT * FROM $EXAMS_TABLE_NAME
            WHERE   
            lab LIKE '%' || :query || '%' 
            OR 
            date LIKE '%' || :query || '%'
        """
    )
    fun searchExams(query: String): Flow<List<ExamModel>>

    @Query("SELECT COUNT(*) = 0 FROM $EXAMS_TABLE_NAME")
    suspend fun isEmpty(): Boolean
}