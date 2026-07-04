package com.vald3nir.myexams.repository.usecases

import com.vald3nir.myexams.repository.datasource.ExamsDataSource
import com.vald3nir.myexams.repository.datasource.LabsDataSource
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LabsUseCase @Inject constructor(
    private val labsDataSource: LabsDataSource,
    private val examsDataSource: ExamsDataSource,
) {

    fun loadLabsFlow(): Flow<List<String>> = flow {
        val labs = labsDataSource.loadLabs()
        emit(labs.mapNotNull { it.name?.takeIf(String::isNotBlank) }.distinct())
    }

    fun loadTopLabsFlow(): Flow<List<String>> = flow {
        val email = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()
        val topLabs = examsDataSource.loadExams(email)
        emit(topLabs.mapNotNull { it.lab?.takeIf(String::isNotBlank) }.distinct())
    }
}