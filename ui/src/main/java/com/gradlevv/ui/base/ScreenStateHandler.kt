package com.gradlevv.ui.base

import androidx.compose.runtime.Composable


@Composable
fun ScreenStateHandler(
    isLoading: Boolean,
    isError: Boolean,
    isEmpty: Boolean = false,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable () -> Unit,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    when {
        isLoading -> loadingContent()
        isError -> errorContent()
        isEmpty -> emptyContent()
        else -> content
    }
}