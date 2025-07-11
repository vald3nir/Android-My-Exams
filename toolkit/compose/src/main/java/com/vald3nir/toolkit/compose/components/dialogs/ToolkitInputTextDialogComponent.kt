package com.vald3nir.toolkit.compose.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitInputTextDialogComponent(
    title: String,
    label: String,
    btnConfirmLabel: String,
    btnCancelLabel: String,
    colors: ScreenColorSchema,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var inputValue by remember { mutableStateOf("") }
    AlertDialog(
        containerColor = colors.dialogBackgroundColor,
        onDismissRequest = onDismissRequest,
        title = {
            ToolkitText.Title(
                text = title,
                textColor = colors.dialogTextColor
            )
        },
        text = {
            OutlinedTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                label = {
                    ToolkitText.Label(
                        text = label,
                        textColor = colors.dialogTextColor
                    )
                }
            )
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                ToolkitText.Label(
                    text = btnCancelLabel,
                    textColor = colors.dialogCancelBtnTextColor
                )
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.dialogConfirmBtnBackgroundColor,
                    contentColor = colors.dialogConfirmBtnBackgroundColor
                ),
                onClick = { onConfirm(inputValue) }) {
                ToolkitText.Label(
                    text = btnConfirmLabel,
                    textColor = colors.dialogConfirmBtnTextColor,
                )
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    ToolkitInputTextDialogComponent(
        title = "Adicionar Item",
        label = "Nome do item",
        btnConfirmLabel = "Adicionar",
        btnCancelLabel = "Cancelar",
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    ToolkitInputTextDialogComponent(
        title = "Adicionar Item",
        label = "Nome do item",
        btnConfirmLabel = "Adicionar",
        btnCancelLabel = "Cancelar",
        colors = DefaultThemeColors().darkColors,
    )
}