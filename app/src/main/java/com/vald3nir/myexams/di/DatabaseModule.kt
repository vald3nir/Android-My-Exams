package com.vald3nir.myexams.di

import android.content.Context
import androidx.room.Room
import com.vald3nir.myexams.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "database.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideExamsDAO(database: AppDatabase) = database.getExamDAO()

    @Provides
    @Singleton
    fun provideLaboratoryDAO(database: AppDatabase) = database.getLaboratoryDAO()

    @Provides
    @Singleton
    fun provideProfileDAO(database: AppDatabase) = database.getProfileDAO()
}