package com.vald3nir.myexams.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.myexams.db.model.ExamModel.Companion.EXAMS_TABLE_NAME

@Entity(tableName = EXAMS_TABLE_NAME)
internal data class ExamModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val enable: Boolean = true,
    val date: String?,
    val lab: String?,
    val totalCholesterol: Int?,
    val HDL: Int?,
    val notHDL: Int?,
    val LDL: Int?,
    val triglycerides: Int?,
    val uricAcid: Float?,
) {
    companion object {
        const val EXAMS_TABLE_NAME = "exams"
    }
}