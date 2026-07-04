package com.vald3nir.myexams.repository.datasource

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

internal class ExamsDataSource @Inject constructor(private val supabase: SupabaseClient) {

    private fun examsOwner(): String = FirebaseAuthenticator.getFirebaseUser()?.email.orEmpty()

    suspend fun loadExams() = supabase.from(TABLE_NAME).select {
        filter { eq(GROUP_KEY, examsOwner()) }
    }.decodeList<ExamDTO>()

    suspend fun loadExamById(examId: String): ExamDTO? {
        return supabase.from(TABLE_NAME).select {
            filter {
                eq("id", examId)
                eq(GROUP_KEY, examsOwner())
            }
        }.decodeList<ExamDTO>().firstOrNull()
    }

    suspend fun insertExam(exam: ExamDTO) {
        supabase.from(TABLE_NAME).insert(exam.copy(owner = examsOwner()))
    }

    suspend fun updateExam(exam: ExamDTO) {
        supabase.from(TABLE_NAME).update(exam.copy(owner = examsOwner())) {
            filter {
                eq("id", exam.id)
                eq(GROUP_KEY, examsOwner())
            }
        }
    }

    companion object {
        private const val TABLE_NAME = "exams"
        private const val GROUP_KEY = "owner"
    }
}