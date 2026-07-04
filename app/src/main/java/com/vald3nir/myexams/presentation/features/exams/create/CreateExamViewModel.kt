package com.vald3nir.myexams.presentation.features.exams.create

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.CreateExamScreenDTO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.enums.CreateExamStep
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
internal class CreateExamViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    parameters: BaseViewModelParameters,
    private val appRepository: AppRepository
) : BaseViewModel(parameters) {

    private val examFlow = MutableStateFlow(ExamDTO())
    private val stepFlow = MutableStateFlow(CreateExamStep.Pdf)

    val screenDataFlow: StateFlow<CreateExamScreenDTO> = combine(
        examFlow,
        appRepository.loadLabsFlow(),
        appRepository.loadTopLabsFlow(),
    ) { exam, labs, topLabs ->
        CreateExamScreenDTO(
            exam = exam,
            labs = labs,
            topLabs = topLabs
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CreateExamScreenDTO(),
    )

    val currentStepFlow: StateFlow<CreateExamStep> = stepFlow.asStateFlow()

    fun onExamChanged(exam: ExamDTO) {
        examFlow.value = exam
    }

    fun goToNextStep() {
        stepFlow.value = when (stepFlow.value) {
            CreateExamStep.Pdf -> CreateExamStep.Date
            CreateExamStep.Date -> CreateExamStep.Lab
            CreateExamStep.Lab -> CreateExamStep.Fields
            CreateExamStep.Fields -> CreateExamStep.Fields
        }
    }

    fun goToPreviousStep() {
        when (stepFlow.value) {
            CreateExamStep.Pdf -> navigateBack()
            CreateExamStep.Date -> stepFlow.value = CreateExamStep.Pdf
            CreateExamStep.Lab -> stepFlow.value = CreateExamStep.Date
            CreateExamStep.Fields -> stepFlow.value = CreateExamStep.Lab
        }
    }

    fun onPdfSelected(pdfPath: String) {
        safeLaunch(
            action = {
                val parsedExam = appRepository.parseExamFromPdf(pdfPath)
                examFlow.value = mergeParsedExam(examFlow.value, parsedExam)
            },
            onSuccessEvent = { stepFlow.value = CreateExamStep.Date },
        )
    }

    fun saveExam() {
        viewModelScope.launch {
            appRepository.insertExam(exam = examFlow.value)
            navigateBack()
        }
    }

    private fun mergeParsedExam(current: ExamDTO, parsed: ExamDTO): ExamDTO {
        return current.copy(
            date = parsed.date ?: current.date,
            lab = parsed.lab ?: current.lab,
            totalCholesterol = parsed.totalCholesterol ?: current.totalCholesterol,
            hdl = parsed.hdl ?: current.hdl,
            notHdl = parsed.notHdl ?: current.notHdl,
            ldl = parsed.ldl ?: current.ldl,
            triglycerides = parsed.triglycerides ?: current.triglycerides,
            uricAcid = parsed.uricAcid ?: current.uricAcid,
        )
    }

    fun readPDF(uri: Uri?) {
        if (uri == null) return
        viewModelScope.launch {
            runCatching {
                copyPdfToCache(context = context, uri = uri)
            }.onSuccess { pdfPath ->
                onPdfSelected(pdfPath)
            }.onFailure { error ->
                if (error.message.isNullOrBlank().not()) {
                    notifyUiMessage(error.message.orEmpty())
                } else {
                    notifyUiMessage(context.getString(R.string.create_exam_pdf_import_error))
                }
            }
        }
    }

    private suspend fun copyPdfToCache(context: Context, uri: Uri): String = withContext(Dispatchers.IO) {
        val cacheFile = File(context.cacheDir, "imported_exam_${System.currentTimeMillis()}.pdf")
        val inputStream = context.contentResolver.openInputStream(uri) ?: error(context.getString(R.string.create_exam_pdf_import_error))
        inputStream.use { input ->
            cacheFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        cacheFile.absolutePath
    }
}