package com.vald3nir.myexams.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LaboratoryModel.LABS_TABLE_NAME)
internal data class LaboratoryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String? = null,
) {
    companion object {
        const val LABS_TABLE_NAME = "labs"
    }
}