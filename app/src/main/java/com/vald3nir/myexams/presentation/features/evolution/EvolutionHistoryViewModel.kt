package com.vald3nir.myexams.presentation.features.evolution

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.validations.getLipidValidationParams
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import com.vald3nir.toolkit.core.utils.extensions.isoToShortDate
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class EvolutionHistoryViewModel @Inject constructor(parameters: BaseViewModelParameters, repository: AppRepository) : BaseViewModel(parameters) {

    val uiModel: StateFlow<List<EvolutionFieldChartDTO>> = combine(repository.listExamsFlow(), repository.loadProfileFlow()) { exams, profile ->
        val sortedExams = exams.orEmpty().reversed()
        val lipidParams = profile?.getLipidValidationParams()
        listOf(
            mapFieldChart(
                exams = sortedExams,
                description = lipidParams?.totalCholesterolMessage.orEmpty(),
                titleRes = R.string.total_cholesterol,
                upperLimit = lipidParams?.totalCholesterolMax?.toFloat(),
                valueSelector = { it.totalCholesterol?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                description = lipidParams?.hdlMessage.orEmpty(),
                titleRes = R.string.hdl_d,
                lowerLimit = lipidParams?.hdlMin?.toFloat(),
                valueSelector = { it.hdl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                description = lipidParams?.notHDLMessage.orEmpty(),
                titleRes = R.string.no_hdl,
                upperLimit = lipidParams?.notHdlMax?.toFloat(),
                valueSelector = { it.notHdl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                description = lipidParams?.ldlMessage.orEmpty(),
                titleRes = R.string.ldl,
                upperLimit = lipidParams?.ldlMax?.toFloat(),
                valueSelector = { it.ldl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                description = lipidParams?.triglyceridesMessage.orEmpty(),
                titleRes = R.string.triglycerides,
                upperLimit = lipidParams?.triglyceridesMax?.toFloat(),
                valueSelector = { it.triglycerides?.toFloat() },
            ),
        )
    }.catch { emit(emptyList()) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    private fun mapFieldChart(
        exams: List<ExamDTO>,
        @StringRes titleRes: Int,
        description: String,
        upperLimit: Float? = null,
        lowerLimit: Float? = null,
        valueSelector: (ExamDTO) -> Float?,
    ): EvolutionFieldChartDTO {
        val points = exams.mapNotNull { exam ->
            val value = valueSelector(exam) ?: return@mapNotNull null
            val label = exam.date?.isoToShortDate() ?: return@mapNotNull null
            val isOutOfLimits = (upperLimit != null && value > upperLimit) || (lowerLimit != null && value < lowerLimit)
            ItemChartDTO(
                value = value,
                label = label,
                color = if (isOutOfLimits) Color(0xFFD0021B) else Color(0xFF4A90E2)
            )
        }
        return EvolutionFieldChartDTO(titleRes = titleRes, description = description, points = points)
    }
}
