package com.vald3nir.myexams.repository.datasource

import com.vald3nir.myexams.domain.dto.ExamDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

internal class ExamsDataSource @Inject constructor(private val supabase: SupabaseClient) {

    suspend fun loadExams(email: String): List<ExamDTO> = supabase.from(TABLE_NAME).select {
        filter { eq(GROUP_KEY, email) }
    }.decodeList<ExamDTO>()

    suspend fun loadExamById(email: String, examId: String): ExamDTO? = supabase.from(TABLE_NAME).select {
        filter {
            eq("id", examId)
            eq(GROUP_KEY, email)
        }
    }.decodeList<ExamDTO>().firstOrNull()

    suspend fun insertExam(exam: ExamDTO) {
        supabase.from(TABLE_NAME).insert(exam)
    }

    suspend fun updateExam(exam: ExamDTO) {
        exam.owner?.let { owner ->
            supabase.from(TABLE_NAME).update(exam) {
                filter {
                    eq("id", exam.id)
                    eq(GROUP_KEY, owner)
                }
            }
        }
    }

    companion object {
        private const val TABLE_NAME = "exams"
        private const val GROUP_KEY = "owner"
    }
}