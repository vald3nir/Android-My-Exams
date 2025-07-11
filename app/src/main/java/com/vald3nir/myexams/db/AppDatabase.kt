package com.vald3nir.myexams.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.myexams.db.dao.ExamDAO
import com.vald3nir.myexams.db.dao.LaboratoryDAO
import com.vald3nir.myexams.db.dao.ProfileDAO
import com.vald3nir.myexams.db.model.ExamModel
import com.vald3nir.myexams.db.model.LaboratoryModel
import com.vald3nir.myexams.db.model.ProfileModel

@Database(
    entities = [ExamModel::class, LaboratoryModel::class, ProfileModel::class],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getExamDAO(): ExamDAO
    abstract fun getLaboratoryDAO(): LaboratoryDAO
    abstract fun getProfileDAO(): ProfileDAO
}