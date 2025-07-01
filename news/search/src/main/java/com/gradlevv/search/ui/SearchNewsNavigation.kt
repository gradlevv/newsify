package com.gradlevv.search.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.searchNewsScreen() {
    composable<SearchNewsDestination> {
        SearchNewsScreen()
    }
}

@Serializable
data object SearchNewsDestination

@Serializable
data object NewsDetailDestination

fun NavController.navigateToSearchScreen() {
    navigate(SearchNewsDestination)
}

fun NavController.navigateToDetailScreen() {
    navigate(NewsDetailDestination)
}

