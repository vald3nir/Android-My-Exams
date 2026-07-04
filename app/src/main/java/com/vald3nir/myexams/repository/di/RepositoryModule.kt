package com.vald3nir.myexams.repository.di

import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.myexams.repository.di.impls.AppRepositoryImpl
import com.vald3nir.myexams.repository.di.impls.AuthenticatedUserRepositoryImpl
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
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
    abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    abstract fun bindAuthenticatedUserRepository(impl: AuthenticatedUserRepositoryImpl): AuthenticatedUserRepository
}