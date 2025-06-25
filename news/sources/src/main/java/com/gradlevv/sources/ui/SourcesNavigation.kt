package com.gradlevv.sources.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.sourcesScreen() {
    composable<NewsSourcesDestination> {
        NewsSourcesScreen()
    }
}

@Serializable
data object NewsSourcesDestination

fun NavController.navigateToSourcesScreen() {
    navigate(NewsSourcesDestination)
}