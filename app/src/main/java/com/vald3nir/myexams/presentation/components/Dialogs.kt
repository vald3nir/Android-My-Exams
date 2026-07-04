package com.vald3nir.myexams.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vald3nir.myexams.R
import com.vald3nir.myexams.domain.enums.genderEnumList
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitInputTextDialog
import com.vald3nir.toolkit.designsystem.components.dialogs.ToolkitSelectDialog

@Composable
internal fun EditProfileNameDialog(
    currentName: String,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitInputTextDialog(
        title = stringResource(R.string.dialog_update_name_title),
        label = stringResource(R.string.dialog_update_name_label),
        value = currentName,
        btnConfirmLabel = stringResource(R.string.btn_update),
        btnCancelLabel = stringResource(R.string.btn_cancel),
        onConfirm = onConfirm,
        onCancel = onCancel,
    )
}

@Composable
internal fun ProfileGenderDialog(
    currentGender: String,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    ToolkitSelectDialog(
        title = stringResource(R.string.dialog_update_gender_title),
        selectedValue = currentGender,
        items = genderEnumList(),
        onConfirm = onConfirm,
        onCancel = onCancel
    )
}