package com.gradlevv.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gradlevv.ui.base.Destination
import kotlinx.serialization.Serializable

fun NavGraphBuilder.newsListScreen(
    navHostController: NavHostController,
    onNavigateToDetail: () -> Unit,
    onNavigateToCategory: () -> Unit,
) {
    composable(NewsListDestination.route) {
        NewsListScreen(
            navController = navHostController,
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToCategory = onNavigateToCategory
        )
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