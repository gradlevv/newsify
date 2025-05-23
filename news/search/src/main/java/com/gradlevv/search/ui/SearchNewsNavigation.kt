package com.gradlevv.search.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNewsScreen() {
    composable<SearchNewsDestination> {
        SearchNewsScreen()
    }
}

fun NavController.navigateToSearchScreen() {
    navigate(SearchNewsDestination)
}

