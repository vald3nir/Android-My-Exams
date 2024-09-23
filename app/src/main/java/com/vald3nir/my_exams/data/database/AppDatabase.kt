package com.vald3nir.my_exams.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vald3nir.my_exams.data.database.daos.ExamDAO
import com.vald3nir.my_exams.data.database.entities.ExamEntity
import com.vald3nir.my_exams.data.database.utils.DateConverter

@Database(
    version = 1,
    exportSchema = true,
    entities = [ExamEntity::class],
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun examDAO(): ExamDAO

}