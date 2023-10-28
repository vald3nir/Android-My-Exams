package com.vald3nir.my_exams.domain.use_cases

import android.annotation.SuppressLint
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.data.repository.ExamRepository
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt

class ExamUseCaseImpl @Inject constructor(private val repository: ExamRepository) : ExamUseCase {

    @SuppressLint("SimpleDateFormat")
    override suspend fun runLiveDemo(
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        val exams = ArrayList<ExamDTO>()
        for (i in 0..5) {
            exams.add(
                ExamDTO(
                    id = i.toLong(),
                    date = currentDate,
                    totalCholesterol = nextInt(100, 200),
                    HDL_D = nextInt(10, 50),
                    NOT_HDL = nextInt(100, 200),
                    LDL = nextInt(100, 200),
                    triglycerides = nextInt(100, 200),
                    uricAcid = nextInt(1, 10).toFloat()
                )
            )
        }
        repository.insertExams(exams, onShowLoading, onSuccess, onError)
    }

    override suspend fun downloadExams(
        key: String,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        onShowLoading?.invoke(true)
        repository.downloadExams(key, onShowLoading, onSuccess, onError)
    }

    override suspend fun loadExamsLocal(
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: (exams: List<ExamDTO>) -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.loadExamsLocal(onShowLoading, onSuccess, onError)
    }

    override suspend fun hasExams() = repository.hasExams()

    override suspend fun deleteExamsLocal(
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.deleteExamsLocal(onSuccess, onError)
    }

    override suspend fun deleteExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.deleteExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun updateExam(
        key: String?,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.updateExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun insertNewExam(
        key: String,
        exam: ExamDTO,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.insertNewExam(key, exam, onShowLoading, onSuccess, onError)
    }

    override suspend fun insertExamsLocal(
        exams: List<ExamDTO>,
        onShowLoading: ((Boolean) -> Unit)?,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        repository.insertExamsLocal(exams, onShowLoading, onSuccess, onError)
    }
}