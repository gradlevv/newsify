package com.gradlevv.newsify.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gradlevv.newsify.navigation.TopLevelScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {}
    ) {
        NavHost(navController, TopLevelScreen.HOME) {

        }
    }
}