package com.gradlevv.list.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.newsListScreen() {
    composable<NewsListDestination> {
        NewsListScreen()
    }
}

@Serializable
data object NewsListDestination