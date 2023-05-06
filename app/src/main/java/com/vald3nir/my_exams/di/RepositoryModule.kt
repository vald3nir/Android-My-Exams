package com.vald3nir.my_exams.di

import com.vald3nir.my_exams.data.repository.ExamRepository
import com.vald3nir.my_exams.data.repository.ExamRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindExamRepository(
        repositoryImpl: ExamRepositoryImpl,
    ): ExamRepository

}