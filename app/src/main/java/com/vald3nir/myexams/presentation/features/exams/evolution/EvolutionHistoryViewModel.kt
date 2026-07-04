package com.vald3nir.myexams.presentation.features.exams.evolution

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.dto.EvolutionFieldChartDTO
import com.vald3nir.myexams.domain.dto.ExamDTO
import com.vald3nir.myexams.domain.validations.getLipidValidationParams
import com.vald3nir.myexams.domain.validations.getUricAcidValidationRange
import com.vald3nir.myexams.repository.AppRepository
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import com.vald3nir.toolkit.designsystem.components.charts.ItemChartDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
internal class EvolutionHistoryViewModel @Inject constructor(parameters: BaseViewModelParameters, repository: AppRepository) : BaseViewModel(parameters) {

    val screenDataFlow: StateFlow<List<EvolutionFieldChartDTO>> = combine(repository.listExams(), repository.loadProfileFlow()) { exams, profile ->
        val sortedExams = exams.sortedBy { exam -> exam.date.toLocalDateOrNull() ?: LocalDate.MAX }
        val completeProfile = profile?.takeUnless { it.needCompleteProfile() }
        val lipidParams = completeProfile?.getLipidValidationParams()
        val uricAcidRange = completeProfile?.getUricAcidValidationRange()

        listOf(
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.total_cholesterol,
                upperLimit = lipidParams?.totalCholesterolMax?.toFloat(),
                valueSelector = { it.totalCholesterol?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.hdl_d,
                lowerLimit = lipidParams?.hdlMin?.toFloat(),
                valueSelector = { it.hdl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.no_hdl,
                upperLimit = lipidParams?.notHdlMax?.toFloat(),
                valueSelector = { it.notHdl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.ldl,
                upperLimit = lipidParams?.ldlMax?.toFloat(),
                valueSelector = { it.ldl?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.triglycerides,
                upperLimit = lipidParams?.triglyceridesMax?.toFloat(),
                valueSelector = { it.triglycerides?.toFloat() },
            ),
            mapFieldChart(
                exams = sortedExams,
                titleRes = R.string.uric_acid,
                upperLimit = uricAcidRange?.endInclusive,
                lowerLimit = uricAcidRange?.start,
                valueSelector = { it.uricAcid?.toFloat() },
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
        upperLimit: Float? = null,
        lowerLimit: Float? = null,
        valueSelector: (ExamDTO) -> Float?,
    ): EvolutionFieldChartDTO {
        val points = exams.mapNotNull { exam ->
            val value = valueSelector(exam) ?: return@mapNotNull null
            val dateLabel = exam.date.toChartLabel() ?: return@mapNotNull null
            ItemChartDTO(
                value = value,
                label = dateLabel,
            )
        }
        return EvolutionFieldChartDTO(
            titleRes = titleRes,
            points = points,
            upperLimit = upperLimit,
            lowerLimit = lowerLimit,
        )
    }

    private fun String?.toChartLabel(): String? {
        if (this.isNullOrBlank()) return null
        return toLocalDateOrNull()?.format(chartLabelFormatter) ?: this
    }

    private fun String?.toLocalDateOrNull(): LocalDate? {
        if (this.isNullOrBlank()) return null
        return runCatching { LocalDate.parse(this, inputDateFormatter) }.getOrNull()
    }

    private companion object {
        val inputDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
        val chartLabelFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.getDefault())
    }
}
