package com.gradlevv.sources.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.sourcesScreen() {
    composable<NewsSourcesDestination> {
        NewsSourcesScreen()
    }
}