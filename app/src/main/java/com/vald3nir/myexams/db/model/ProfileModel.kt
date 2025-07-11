package com.vald3nir.myexams.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.myexams.db.model.ProfileModel.Companion.PROFILE_TABLE_NAME

@Entity(tableName = PROFILE_TABLE_NAME)
internal data class ProfileModel(
    @PrimaryKey
    val id: Long = 0,
    val email: String?,
    val userImage: String?,
    val birthday: String?,
    val gender: String?,
) {
    companion object {
        const val PROFILE_TABLE_NAME = "profile"
    }
}