package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.myexams.domain.dto.LaboratoryDTO
import com.vald3nir.myexams.repository.LabsRepository
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputComponent
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputData
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class InputProductNameViewModel @Inject constructor(repository: LabsRepository) : BaseViewModel() {
    val laboratories: Flow<List<LaboratoryDTO>> = repository.getAllLaboratories().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}


@Composable
internal fun ComponentSelectLaboratory(
    inputValue: String,
    useTransparentBackend: Boolean = false,
    onSelected: (LaboratoryDTO) -> Unit,
    colors: ScreenColorSchema,
) {
    val viewModel = hiltViewModel<InputProductNameViewModel>()
    val laboratories by viewModel.laboratories.collectAsState(initial = emptyList())
    ToolkitAutoCompleteInputComponent(
        useTransparentBackend = useTransparentBackend,
        inputValue = inputValue,
        suggestionList = laboratories.formatProducts(),
        colors = colors,
        label = "Nome do laboratório",
        placeholder = "Laboratório",
        startIcon = ToolkitIcons.Science,
        onSelected = { productName ->
            onSelected(laboratories.findProductByName(productName))
        },
    )
}

private fun List<LaboratoryDTO>.findProductByName(name: String): LaboratoryDTO {
    val formattedName = name.lowercase().trim()
    return firstOrNull { it.name?.lowercase()?.trim() == formattedName } ?: LaboratoryDTO(name = name)
}

private fun List<LaboratoryDTO>.formatProducts(): List<ToolkitAutoCompleteInputData> {
    val labs = arrayListOf<ToolkitAutoCompleteInputData>()
    forEachIndexed { index, item ->
        labs.add(ToolkitAutoCompleteInputData(id = index.toLong(), text = item.name.orEmpty()))
    }
    return labs
}