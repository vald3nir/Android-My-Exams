package com.vald3nir.toolkit.compose.designSystem

import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema


data class DefaultThemeColors(
    val lightColors: ScreenColorSchema = ScreenColorSchema(
        // Colors for main screens
        backgroundColor = Color.White,
        textColor = Color.Black,
        linkColor = Color.Blue,
        warningTextColor = Color.Red,
        iconTint = Color.Blue,

        // Colors for buttons
        buttonBackgroundColor = Color.Blue,
        buttonBackgroundDisableColor = Color.LightGray,
        buttonTextColor = Color.White,

        // Colors for toolbars
        toolbarBackgroundColor = Color(0xFFF6EEEE),
        toolbarTextColor = Color.Black,
        toolbarIconTint = Color.Black,
        dividerOverlayColor = Color.LightGray,

        // Colors for item in a list
        overlayColor = Color(0xFFE5DFDF),
        textOverlayColor = Color.Black,
        linkOverlayColor = Color.Blue,
        iconOverlayTint = Color.Black,

        // Colors for checkbox components
        checkedColor = Color(0xFF2E2EE8),
        checkmarkColor = Color.White,
        uncheckedColor = Color(0xFF2E2EE8),
        disabledColor = Color.Gray,

        // Colors for Dialogs
        dialogBackgroundColor = Color.White,
        dialogTextColor = Color.Black,
        dialogConfirmBtnTextColor = Color.White,
        dialogConfirmBtnBackgroundColor = Color.Blue,
        dialogCancelBtnTextColor = Color.Black,
        dialogCancelBtnBackgroundColor = Color.White,

        // Colors for Switch
        switchEnableColor = Color.Blue,
        switchDisableColor = Color.Gray,
    ),
    val darkColors: ScreenColorSchema = ScreenColorSchema(
        // Colors for main screens
        backgroundColor = Color.Black,
        textColor = Color.White,
        warningTextColor = Color.Red,
        linkColor = Color.Yellow,
        iconTint = Color.White,

        // Colors for buttons
        buttonBackgroundColor = Color.White,
        buttonBackgroundDisableColor = Color.LightGray,
        buttonTextColor = Color.Black,

        // Colors for toolbars
        toolbarBackgroundColor = Color.DarkGray,
        toolbarTextColor = Color.White,
        toolbarIconTint = Color.White,

        // Colors for item in a list
        overlayColor = Color.DarkGray,
        textOverlayColor = Color.White,
        linkOverlayColor = Color.Yellow,
        iconOverlayTint = Color.White,
        dividerOverlayColor = Color.White,

        // Colors for checkbox components
        checkedColor = Color.White,
        checkmarkColor = Color.Black,
        uncheckedColor = Color.White,
        disabledColor = Color.Gray,

        // Colors for Dialogs
        dialogBackgroundColor = Color.DarkGray,
        dialogTextColor = Color.White,
        dialogConfirmBtnTextColor = Color.White,
        dialogConfirmBtnBackgroundColor = Color.Gray,
        dialogCancelBtnTextColor = Color.White,
        dialogCancelBtnBackgroundColor = Color.DarkGray,

        // Colors for Switch
        switchEnableColor = Color.Yellow,
        switchDisableColor = Color.LightGray,
    ),
)