package com.vald3nir.myexams.repository.di.impls

import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.dto.ProfileDTO
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.myexams.repository.usecases.ExamsUseCase
import com.vald3nir.myexams.repository.usecases.LabsUseCase
import com.vald3nir.myexams.repository.usecases.ProfileUseCase
import javax.inject.Inject

internal class AppRepositoryImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val examsUseCase: ExamsUseCase,
    private val labsUseCase: LabsUseCase,
) : AppRepository {

    override suspend fun loadProfile() = profileUseCase.loadProfile()

    override fun loadProfileFlow() = profileUseCase.loadProfileFlow()

    override suspend fun updateProfile(profile: ProfileDTO) = profileUseCase.updateProfile(profile)

    override suspend fun completeProfile(birthday: String?, gender: String?) = profileUseCase.completeProfile(birthday, gender)

    override fun listExamsFlow() = examsUseCase.examsStateFlow()

    override fun loadLabsFlow() = labsUseCase.loadLabsFlow()

    override fun loadTopLabsFlow() = labsUseCase.loadTopLabsFlow()

    override fun getExamById(examId: String) = examsUseCase.getExamById(examId)

    override suspend fun parseExamFromPdf(pdfPath: String) = examsUseCase.parseExamFromPdf(pdfPath)

    override suspend fun insertExam(exam: ExamDTO) = examsUseCase.insertExam(exam)

    override suspend fun updateExam(exam: ExamDTO) = examsUseCase.updateExam(exam)
}