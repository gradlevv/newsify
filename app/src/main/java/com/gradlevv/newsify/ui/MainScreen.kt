package com.gradlevv.newsify.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gradlevv.newsify.navigation.NewsifyNavigationBar
import com.gradlevv.newsify.navigation.RootScreen
import com.gradlevv.newsify.navigation.TopLevelScreen


@Composable
fun MainScreen(
    modifier: Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NewsifyNavigationBar(
                destinations = TopLevelScreen.entries,
                onNavigationSelected = { destination ->
                    val navOptions = navOptions {
                        launchSingleTop = true
                    }
                    when (destination) {
                        TopLevelScreen.HOME -> {
                            navController.navigate(RootScreen.Home.route, navOptions)
                        }

                        TopLevelScreen.SOURCES -> {
                            navController.navigate(RootScreen.Sources.route, navOptions)
                        }

                        TopLevelScreen.SEARCH -> {
                            navController.navigate(RootScreen.Search.route, navOptions)
                        }

                        TopLevelScreen.SETTING -> {
                            navController.navigate(RootScreen.Settings.route, navOptions)
                        }
                    }
                },
                modifier = modifier
            )
        }
    ) { padding ->

    }
}