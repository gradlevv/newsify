package com.gradlevv.newsify.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gradlevv.list.ui.NewsListDestination
import com.gradlevv.list.ui.navigateToListScreen
import com.gradlevv.list.ui.newsListScreen
import com.gradlevv.newsify.navigation.NewsifyNavigationBar
import com.gradlevv.search.ui.navigateToSearchScreen
import com.gradlevv.search.ui.searchNewsScreen
import com.gradlevv.setting.ui.navigateToSettingScreen
import com.gradlevv.setting.ui.settingScreen
import com.gradlevv.sources.ui.navigateToSourcesScreen
import com.gradlevv.sources.ui.sourcesScreen


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NewsifyNavigationBar(
                hierarchy = navController.currentBackStackEntryAsState().value
                    ?.destination?.hierarchy,
                onNavigateToHomeClick = { navController.navigateToListScreen() },
                onNavigateToSearchClick = { navController.navigateToSearchScreen() },
                onNavigateToSourcesClick = { navController.navigateToSourcesScreen() },
                onNavigateToSettingClick = { navController.navigateToSettingScreen() },
            )
        }
    ) { padding ->
        NavHost(
            modifier = modifier.padding(padding),
            navController = navController,
            startDestination = NewsListDestination
        ) {
            newsListScreen()
            searchNewsScreen()
            settingScreen()
            sourcesScreen()
        }
    }
}

