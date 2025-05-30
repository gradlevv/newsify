package com.gradlevv.newsify.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gradlevv.list.ui.NewsListDestination
import com.gradlevv.list.ui.navigateToListScreen
import com.gradlevv.list.ui.newsListScreen
import com.gradlevv.newsify.R
import com.gradlevv.newsify.navigation.NewsifyNavigationBar
import com.gradlevv.search.ui.navigateToSearchScreen
import com.gradlevv.search.ui.searchNewsScreen
import com.gradlevv.setting.ui.navigateToSettingScreen
import com.gradlevv.setting.ui.settingScreen
import com.gradlevv.sources.ui.navigateToSourcesScreen
import com.gradlevv.sources.ui.sourcesScreen
import com.gradlevv.ui.theme.ColorPrimary


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsifyTitleTopBar(
                title = stringResource(R.string.newsify_top_bar_title)
            )
        },
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


@Composable
fun NewsifyTitleTopBar(
    modifier: Modifier = Modifier,
    title: String
) {

    Column(
        modifier = modifier.padding(top = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = title,
            color = ColorPrimary,
            fontSize = 26.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}

