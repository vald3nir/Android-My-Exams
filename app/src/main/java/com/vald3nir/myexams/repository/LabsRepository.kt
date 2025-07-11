package com.vald3nir.myexams.repository

import com.vald3nir.myexams.db.dao.LaboratoryDAO
import com.vald3nir.myexams.domain.dto.LaboratoryDTO
import com.vald3nir.myexams.domain.mappers.toDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface LabsRepository {
    fun getAllLaboratories(): Flow<List<LaboratoryDTO>>
}

internal class LabsRepositoryImpl @Inject constructor(private val dao: LaboratoryDAO) : LabsRepository {

    override fun getAllLaboratories() = dao.getAllLabs().map { it.map { entity -> entity.toDTO() } }
}
