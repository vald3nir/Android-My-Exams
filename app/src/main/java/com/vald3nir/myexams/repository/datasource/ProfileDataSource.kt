package com.vald3nir.myexams.repository.datasource

import com.vald3nir.myexams.domain.dto.ProfileDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

internal class ProfileDataSource @Inject constructor(private val supabase: SupabaseClient) {

    suspend fun loadProfile(email: String): ProfileDTO? = runCatching {
        supabase.from(TABLE_NAME).select {
            filter { eq(GROUP_KEY, email) }
        }.decodeSingle<ProfileDTO?>()
    }.getOrElse { null }


    suspend fun insertProfile(profile: ProfileDTO) = runCatching {
        supabase.from(TABLE_NAME).insert(profile) {
            filter {
                eq("id", profile.id)
            }
        }
    }.onFailure {
        it.printStackTrace()
    }

    suspend fun updateProfile(profile: ProfileDTO) = runCatching {
        supabase.from(TABLE_NAME).update(profile) {
            filter {
                eq("id", profile.id)
            }
        }
    }.onFailure {
        it.printStackTrace()
    }

    companion object {
        private const val TABLE_NAME = "profile"
        private const val GROUP_KEY = "email"
    }

}