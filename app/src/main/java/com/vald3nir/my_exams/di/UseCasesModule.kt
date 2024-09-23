package com.vald3nir.my_exams.di

import com.vald3nir.my_exams.domain.use_cases.ExamUseCase
import com.vald3nir.my_exams.domain.use_cases.ExamUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModule {

    @ViewModelScoped
    @Binds
    abstract fun bindExamUseCase(
        useCaseImpl: ExamUseCaseImpl,
    ): ExamUseCase

}