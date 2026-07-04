package com.vald3nir.myexams.repository.datasource

import com.vald3nir.myexams.domain.dto.LaboratoryDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

internal class LabsDataSource @Inject constructor(private val supabase: SupabaseClient) {

    suspend fun loadLabs() = supabase.from(TABLE_NAME).select().decodeList<LaboratoryDTO>()

    companion object {
        private const val TABLE_NAME = "labs"
    }
}