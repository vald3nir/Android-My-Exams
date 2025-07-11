package com.vald3nir.toolkit.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ComponentLogoutUserDialog(colors: ScreenColorSchema, onDismissRequest: () -> Unit = {}, onConfirm: () -> Unit = {}) {
    AlertDialog(
        backgroundColor = colors.dialogBackgroundColor,
        onDismissRequest = { onDismissRequest() },
        title = {
            ToolkitText.Title(
                text = stringResource(R.string.dialog_change_user_title),
                textColor = colors.dialogTextColor
            )
        },
        text = {
            ToolkitText.Label(
                text = stringResource(R.string.dialog_change_user_description),
                textColor = colors.dialogTextColor
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                ToolkitText.Label(
                    text = stringResource(R.string.dialog_change_user_btn_confirm),
                    textColor = colors.dialogTextColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                ToolkitText.Label(
                    text = stringResource(R.string.dialog_change_user_btn_cancel),
                    textColor = colors.dialogTextColor
                )
            }
        }
    )
}


@Preview
@Composable
private fun PreviewLight() {
    Column {
        val colors = DefaultThemeColors()
        ComponentLogoutUserDialog(colors = colors.lightColors)
    }
}

@Preview
@Composable
private fun PreviewDark() {
    Column {
        val colors = DefaultThemeColors()
        ComponentLogoutUserDialog(colors = colors.darkColors)
    }
}