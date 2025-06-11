package com.gradlevv.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.categoryScreen(navHostController: NavHostController) {
    composable<CategoryDestination> {
        NewsCategoryScreen(navHostController)
    }
}

@Serializable
data object CategoryDestination

fun NavController.navigateToCategoryScreen() {
    navigate(CategoryDestination)
}