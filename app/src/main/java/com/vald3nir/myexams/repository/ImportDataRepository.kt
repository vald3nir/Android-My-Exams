package com.vald3nir.myexams.repository


import android.content.Context
import com.vald3nir.android.firebase.utils.notifyLog
import com.vald3nir.myexams.db.dao.ExamDAO
import com.vald3nir.myexams.db.dao.LaboratoryDAO
import com.vald3nir.myexams.db.dao.ProfileDAO
import com.vald3nir.myexams.db.model.LaboratoryModel
import javax.inject.Inject

internal interface ImportDataRepository {
    suspend fun hasProfileEdited(): Boolean
    suspend fun importProfileFromServer()
    suspend fun importExamsFromServer()
    suspend fun importLaboratoriesFromServer(context: Context)
    suspend fun importLaboratoriesFromLocal(context: Context)
}

internal class ImportDataRepositoryImpl @Inject constructor(
    private val examsDao: ExamDAO,
    private val laboratoryDAO: LaboratoryDAO,
    private val profileDao: ProfileDAO,
) : ImportDataRepository {

    override suspend fun hasProfileEdited() = !profileDao.isEmpty()

    override suspend fun importProfileFromServer() {
        kotlin.runCatching {
            if (profileDao.isEmpty()) {
                FirebaseUseCase.importProfile()?.let { model ->
                    profileDao.upsert(model)
                }
            }
        }.onFailure {
            it.notifyLog()
        }
    }

    override suspend fun importExamsFromServer() {
        kotlin.runCatching {
            if (examsDao.isEmpty()) {
                val response = FirebaseUseCase.importExams()
                examsDao.clearAndInsert(response)
            }
        }.onFailure {
            it.notifyLog()
        }
    }

    override suspend fun importLaboratoriesFromServer(context: Context) {
        runCatching {
            val response = FirebaseUseCase.importLaboratories()
            laboratoryDAO.clearAndInsert(response)
        }.onFailure {
            it.notifyLog()
            importLaboratoriesFromLocal(context)
        }
    }

    override suspend fun importLaboratoriesFromLocal(context: Context) {
        val labs = mutableListOf<LaboratoryModel>()
        runCatching {
            context.assets.open("laboratories.csv").bufferedReader().useLines { lines ->
                lines.drop(1).forEach { line -> // Ignore the header line
                    val fields = line.split(",")
                    if (fields.isNotEmpty()) {
                        labs.add(LaboratoryModel(name = fields[0].trim()))
                    }
                }
            }
        }.onFailure { it.notifyLog() }
        laboratoryDAO.clearAndInsert(labs)
    }
}