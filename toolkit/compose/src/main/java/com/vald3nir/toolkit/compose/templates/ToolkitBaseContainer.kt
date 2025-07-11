package com.vald3nir.toolkit.compose.templates

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.defaultSpace

@Composable
fun ToolkitBaseContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    topBarContent: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {}, // todo valdenir renomear para bootomContainer
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = backgroundColor,
        contentColor = backgroundColor,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(defaultSpace),
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    DefaultSpaceHeight()
                }
                topBarContent()
            }
        },
        floatingActionButton = floatingActionButton,
        bottomBar = bottomBar,
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = defaultSpace),
                hostState = snackBarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
                .padding(defaultSpace),
            content = content
        )
    }
}