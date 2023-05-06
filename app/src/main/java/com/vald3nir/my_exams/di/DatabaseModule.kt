package com.vald3nir.my_exams.di

import android.content.Context
import androidx.room.Room
import com.vald3nir.my_exams.data.database.AppDatabase
import com.vald3nir.my_exams.data.database.daos.ExamDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "database.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun providesSensorDAO(
        database: AppDatabase,
    ): ExamDAO = database.examDAO()

}