package com.vald3nir.toolkit.helpers.baseclasses

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

private const val BACK_STACK_MESSAGE_KEY_PARAM = "BACK_STACK_MESSAGE_KEY_PARAM"

open class BaseScreenScope(private val viewModel: BaseViewModel? = null, private val navController: NavController? = null) {

    @Composable
    fun CollectUiState(
        onLoading: (Boolean) -> Unit = {},
        onCallbackScreen: (Any?) -> Unit = {},
        onShowMessage: (String) -> Unit = {},
    ) {
        LaunchedEffect(Unit) {
            viewModel?.uiState?.collect { uiEvent ->
                when (uiEvent) {
                    is BaseScreenState.Loading -> onLoading(uiEvent.show)
                    is BaseScreenState.CallbackScreen -> onCallbackScreen(uiEvent.response)
                    is BaseScreenState.ShowMessage -> uiEvent.message?.let { onShowMessage(it) }
                }
            }
        }
    }

    @Composable
    fun NavigationObserver() {
        viewModel?.navigationEvent?.collectAsState(initial = null)?.value?.let { navigationValue ->
            NavigatorTo(navigationValue)
        }
    }

    @Composable
    private fun NavigatorTo(destination: Any) {
        LaunchedEffect(destination) {
            navController?.navigate(destination)
        }
    }

    fun onBackPressed(message: String? = null) {
        navController?.previousBackStackEntry?.savedStateHandle?.set(BACK_STACK_MESSAGE_KEY_PARAM, message)
        navController?.popBackStack()
    }

    fun getBackPressedMessage(): String? {
        val message: String? = navController?.currentBackStackEntry?.savedStateHandle?.get(BACK_STACK_MESSAGE_KEY_PARAM)
        navController?.currentBackStackEntry?.savedStateHandle?.set(BACK_STACK_MESSAGE_KEY_PARAM, null)
        return message
    }


    @Composable
    fun SnackbarHostState.ShowBackPressedMessage() {
        LaunchedEffect(Unit) {
            getBackPressedMessage()?.let { message ->
                showSnackbar(message)
            }
        }
    }
}