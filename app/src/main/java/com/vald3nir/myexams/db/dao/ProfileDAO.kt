package com.vald3nir.myexams.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.vald3nir.myexams.db.model.ProfileModel
import com.vald3nir.myexams.db.model.ProfileModel.Companion.PROFILE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ProfileDAO {
    @Query("DELETE FROM $PROFILE_TABLE_NAME")
    suspend fun clean()

    @Upsert
    suspend fun upsert(entity: ProfileModel): Long

    @Query("SELECT * FROM $PROFILE_TABLE_NAME LIMIT 1")
    fun getProfile(): Flow<ProfileModel?>

    @Query("SELECT COUNT(*) = 0 FROM $PROFILE_TABLE_NAME")
    suspend fun isEmpty(): Boolean
}