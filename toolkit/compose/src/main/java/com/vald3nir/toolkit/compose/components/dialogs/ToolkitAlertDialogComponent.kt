package com.vald3nir.toolkit.compose.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitAlertDialogComponent(
    title: String,
    description: String? = null,
    btnConfirmLabel: String,
    btnCancelLabel: String,
    colors: ScreenColorSchema,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
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
            if (!description.isNullOrBlank()) {
                ToolkitText.Label(
                    text = description,
                    textColor = colors.dialogTextColor
                )
            }
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
                onClick = { onConfirm() }) {
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
    ToolkitAlertDialogComponent(
        title = "Deseja remover o item?",
        description = "o item xxxx será removido",
        btnConfirmLabel = "Remover",
        btnCancelLabel = "Cancelar",
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    ToolkitAlertDialogComponent(
        title = "Deseja remover o item?",
         description = "o item xxxx será removido",
        btnConfirmLabel = "Remover",
        btnCancelLabel = "Cancelar",
        colors = DefaultThemeColors().darkColors,
    )
}