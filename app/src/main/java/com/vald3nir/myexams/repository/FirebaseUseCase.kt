package com.vald3nir.myexams.repository

import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.android.firebase.utils.notifyLog
import com.vald3nir.android.firebase.utils.parseEmailToKey
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.myexams.db.model.ExamModel
import com.vald3nir.myexams.db.model.ProfileModel
import com.vald3nir.myexams.domain.mappers.toExamsModel
import com.vald3nir.myexams.domain.mappers.toLaboratoryModel
import com.vald3nir.myexams.domain.mappers.toModel

internal object FirebaseUseCase {

    private fun getKey() = FirebaseAuthenticator.getFirebaseUser()?.email?.parseEmailToKey()

    suspend fun importLaboratories() = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/laboratories").toLaboratoryModel()

    suspend fun importExams(): List<ExamModel> {
        return getKey()?.let { key ->
            FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/clients/$key/exams").toExamsModel()
        } ?: emptyList()
    }

    fun exportExams(exams: List<ExamModel?>) {
        kotlin.runCatching {
            getKey()?.let { key ->
                FirebaseDB.insertOrUpdate(path = "/${BuildConfig.FLAVOR}/clients/$key/exams", data = exams)
            }
        }.onFailure { it.notifyLog() }
    }

    suspend fun importProfile(): ProfileModel? {
        return getKey()?.let { key ->
            FirebaseDB.readObject(path = "/${BuildConfig.FLAVOR}/clients/$key/profile").toModel()
        }
    }

    fun exportProfile(profile: ProfileModel) {
        kotlin.runCatching {
            getKey()?.let { key ->
                FirebaseDB.insertOrUpdate(path = "/${BuildConfig.FLAVOR}/clients/$key/profile", data = profile)
            }
        }.onFailure { it.notifyLog() }
    }
}