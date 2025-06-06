package com.gradlevv.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradlevv.ui.base.Destination
import kotlinx.serialization.Serializable

fun NavGraphBuilder.newsListScreen(
    onNavigateToDetail: () -> Unit
) {
    composable(NewsListDestination.route) {
        NewsListScreen(onNavigateToDetail = onNavigateToDetail)
    }
}

@Serializable
data object NewsListDestination : Destination {
    override val route = "NewsListDestination"
}

@Serializable
data object NewsGraph : Destination {
    override val route = "NewGraph"
}

fun NavController.navigateToNewsGraph() {
    navigate(NewsGraph.route)
}