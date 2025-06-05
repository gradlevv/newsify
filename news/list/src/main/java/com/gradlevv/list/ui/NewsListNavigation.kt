package com.gradlevv.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradlevv.list.domain.TopHeadLinesItem
import kotlinx.serialization.Serializable

fun NavGraphBuilder.newsListScreen(
    onNavigateToDetail: (item: TopHeadLinesItem) -> Unit
) {
    composable<NewsListDestination> {
        NewsListScreen(onNavigateToDetail = onNavigateToDetail)
    }
}

@Serializable
data object NewsListDestination

fun NavController.navigateToListScreen() {
    navigate(NewsListDestination)
}