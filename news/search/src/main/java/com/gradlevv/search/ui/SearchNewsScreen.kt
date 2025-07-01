package com.gradlevv.search.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Composable
fun SearchNewsScreen(
    navHostController: NavHostController,
    onNavigateToDetail: () -> Unit
) {
    Text("SearchNewsScreen")
}
