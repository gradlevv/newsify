package com.gradlevv.search.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gradlevv.ui.base.Destination
import kotlinx.serialization.Serializable

fun NavGraphBuilder.searchNewsScreen(
    navController: NavHostController,
    onNavigateToDetail: () -> Unit
) {
    composable<SearchNewsDestination> {
        SearchNewsScreen(
            navHostController = navController,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Serializable
data object SearchNewsGraph : Destination {
    override val route: String
        get() = "SearchNewsGraph"
}

@Serializable
data object SearchNewsDestination : Destination {
    override val route: String
        get() = "SearchNewsDestination"
}

@Serializable
data object NewsDetailDestination

fun NavController.navigateToSearchScreen() {
    navigate(SearchNewsDestination)
}

fun NavController.navigateToDetailScreen2() {
    navigate(NewsDetailDestination)
}

