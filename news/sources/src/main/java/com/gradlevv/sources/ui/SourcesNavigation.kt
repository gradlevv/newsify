package com.gradlevv.sources.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.sourcesScreen() {
    composable<NewsSourcesDestination> {
        NewsSourcesScreen()
    }
}

fun NavController.navigateToSourcesScreen() {
    navigate(NewsSourcesDestination)
}