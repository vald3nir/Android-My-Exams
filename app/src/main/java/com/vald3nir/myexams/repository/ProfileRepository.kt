package com.vald3nir.myexams.repository

import com.vald3nir.myexams.db.dao.ProfileDAO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.domain.mappers.toDTO
import com.vald3nir.myexams.domain.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface ProfileRepository {
    suspend fun clean()
    fun getProfile(): Flow<ProfileDTO?>
    suspend fun saveProfile(profile: ProfileDTO)
}

internal class ProfileRepositoryImpl @Inject constructor(private val dao: ProfileDAO) : ProfileRepository {

    override suspend fun clean() {
        dao.clean()
    }

    override fun getProfile(): Flow<ProfileDTO?> {
        return dao.getProfile().map {
            it?.toDTO()
        }
    }

    override suspend fun saveProfile(profile: ProfileDTO) {
        val model = profile.toModel()
        dao.upsert(model)
        FirebaseUseCase.exportProfile(model)
    }
}