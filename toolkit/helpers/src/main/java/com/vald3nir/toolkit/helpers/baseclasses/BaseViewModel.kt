package com.vald3nir.toolkit.helpers.baseclasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.utils.notifyLog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _uiState = Channel<BaseScreenState>()
    val uiState = _uiState.receiveAsFlow()

    private val _navigationEvent = MutableSharedFlow<Any>()
    val navigationEvent: Flow<Any> = _navigationEvent

    fun updateViewState(newState: BaseScreenState) {
        viewModelScope.launch {
            _uiState.send(newState)
        }
    }

    fun emitNavigationEvent(navigationEvent: Any) {
        viewModelScope.launch {
            _navigationEvent.emit(navigationEvent)
        }
    }

    fun launchWithScope(action: suspend () -> Unit) {
        viewModelScope.launch {
            runCatching {
                updateViewState(BaseScreenState.Loading(true))
                action.invoke()
            }.onFailure {
                it.notifyLog()
                updateViewState(BaseScreenState.Loading(false))
                updateViewState(BaseScreenState.ShowMessage(it.message))
            }
        }
    }
}