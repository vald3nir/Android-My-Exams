package com.vald3nir.myexams.di

import com.vald3nir.myexams.repository.ExamsRepository
import com.vald3nir.myexams.repository.ExamsRepositoryImpl
import com.vald3nir.myexams.repository.ImportDataRepository
import com.vald3nir.myexams.repository.ImportDataRepositoryImpl
import com.vald3nir.myexams.repository.LabsRepository
import com.vald3nir.myexams.repository.LabsRepositoryImpl
import com.vald3nir.myexams.repository.ProfileRepository
import com.vald3nir.myexams.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExamsRepository(impl: ExamsRepositoryImpl): ExamsRepository

    @Binds
    @Singleton
    abstract fun bindLabsRepository(impl: LabsRepositoryImpl): LabsRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindImportDataRepository(impl: ImportDataRepositoryImpl): ImportDataRepository

}