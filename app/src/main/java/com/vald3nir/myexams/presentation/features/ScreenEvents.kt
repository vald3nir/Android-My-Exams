package com.vald3nir.myexams.presentation.features

sealed class ScreenEvents {
    object GoHome : ScreenEvents()
    object UserLogout : ScreenEvents()
    object ExamUpdated : ScreenEvents()
    object ExamDeleted : ScreenEvents()
}