package com.vald3nir.myexams.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.myexams.db.model.LaboratoryModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LaboratoryDAO {

    @Query("DELETE FROM ${LaboratoryModel.LABS_TABLE_NAME}")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<LaboratoryModel>): List<Long>

    @Transaction
    suspend fun clearAndInsert(entities: List<LaboratoryModel>) {
        deleteAll()
        insert(entities)
    }

    @Query("SELECT * FROM ${LaboratoryModel.LABS_TABLE_NAME} WHERE name LIKE '%' || :name || '%' LIMIT 10")
    suspend fun getProductsByName(name: String): List<LaboratoryModel>

    @Query("SELECT * FROM ${LaboratoryModel.LABS_TABLE_NAME}")
    fun getAllLabs(): Flow<List<LaboratoryModel>>

    @Query("SELECT COUNT(*) = 0 FROM ${LaboratoryModel.LABS_TABLE_NAME}")
    suspend fun isEmpty(): Boolean
}