package com.vald3nir.toolkit.helpers.baseclasses

sealed interface BaseScreenState {
    data class Loading(val show: Boolean) : BaseScreenState
    data class CallbackScreen(val response: Any? = null) : BaseScreenState
    data class ShowMessage(val message: String?) : BaseScreenState
}