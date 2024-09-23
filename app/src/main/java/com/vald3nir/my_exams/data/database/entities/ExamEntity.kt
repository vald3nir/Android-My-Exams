package com.vald3nir.my_exams.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.my_exams.data.database.utils.EntityConstant.EXAM_TABLE_NAME

@Entity(tableName = EXAM_TABLE_NAME)
data class ExamEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var date: String?,
    var totalCholesterol: Int?,
    var HDL_D: Int?,
    var NOT_HDL: Int?,
    var LDL: Int?,
    var triglycerides: Int?,
    var uricAcid: Float?,
)