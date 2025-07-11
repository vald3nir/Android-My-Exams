package com.vald3nir.toolkit.compose.designSystem.schema

import androidx.compose.ui.graphics.Color

data class ScreenColorSchema(

    // Colors for main components
    val backgroundColor: Color,
    val textColor: Color,
    val warningTextColor: Color,
    val linkColor: Color,
    val iconTint: Color,

    // Colors for overlays components, like: item list, boxes and menus
    val overlayColor: Color,
    val iconOverlayTint: Color,
    val textOverlayColor: Color,
    val linkOverlayColor: Color,
    val dividerOverlayColor: Color,

    // Colors for buttons
    val buttonBackgroundColor: Color,
    val buttonBackgroundDisableColor: Color,
    val buttonTextColor: Color,

    // Colors for toolbars
    val toolbarBackgroundColor: Color,
    val toolbarTextColor: Color,
    val toolbarIconTint: Color,

    // Colors for checkbox components
    val checkedColor: Color,            // background when checked
    val checkmarkColor: Color,          // color of "✓"
    val uncheckedColor: Color,          // border color when unchecked
    val disabledColor: Color,

    // Colors for Dialogs
    val dialogBackgroundColor: Color,
    val dialogTextColor: Color,
    val dialogConfirmBtnTextColor: Color,
    val dialogConfirmBtnBackgroundColor: Color,
    val dialogCancelBtnTextColor: Color,
    val dialogCancelBtnBackgroundColor: Color,

    // Colors for Switch
    val switchEnableColor: Color,
    val switchDisableColor: Color,
)