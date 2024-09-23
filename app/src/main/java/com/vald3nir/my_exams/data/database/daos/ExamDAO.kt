package com.vald3nir.my_exams.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vald3nir.my_exams.data.database.entities.ExamEntity
import com.vald3nir.my_exams.data.database.utils.EntityConstant.EXAM_TABLE_NAME

@Dao
interface ExamDAO {

    @Query(value = "SELECT * FROM $EXAM_TABLE_NAME")
    fun getExams(): List<ExamEntity>

    @Query("SELECT COUNT(*) FROM $EXAM_TABLE_NAME")
    fun size(): Int

    @Query("DELETE FROM $EXAM_TABLE_NAME")
    fun clear()

    @Query("DELETE FROM $EXAM_TABLE_NAME WHERE id = :id")
    fun deleteExamById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExam(entity: ExamEntity?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateExam(entity: ExamEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExams(entity: List<ExamEntity>)
}