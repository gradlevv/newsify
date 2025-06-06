package com.gradlevv.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.newsDetailScreen(navHostController: NavHostController) {
    composable<NewsDetailDestination> {
        NewsDetailScreen(navHostController)
    }
}


@Serializable
data object NewsDetailDestination

fun NavController.navigateToDetailScreen() {
    navigate(NewsDetailDestination)
}