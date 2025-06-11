package com.gradlevv.newsify.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.gradlevv.list.ui.top.NewsListDestination
import com.gradlevv.newsify.ui.R
import com.gradlevv.search.ui.SearchNewsDestination
import com.gradlevv.setting.ui.SettingDestination
import com.gradlevv.sources.ui.NewsSourcesDestination


@Composable
fun NewsifyNavigationBar(
    hierarchy: Sequence<NavDestination>?,
    onNavigateToHomeClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
    onNavigateToSourcesClick: () -> Unit,
    onNavigateToSettingClick: () -> Unit,
) {
    NavigationBar {

        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(NewsListDestination::class) } == true,
            onClick = onNavigateToHomeClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_home_fill),
                    contentDescription = ""
                )
            }
        )

        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(SearchNewsDestination::class) } == true,
            onClick = onNavigateToSearchClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search_fill),
                    contentDescription = ""
                )
            }
        )

        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(NewsSourcesDestination::class) } == true,
            onClick = onNavigateToSourcesClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_global_fill),
                    contentDescription = ""
                )
            }
        )

        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(SettingDestination::class) } == true,
            onClick = onNavigateToSettingClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_settings_fill),
                    contentDescription = ""
                )
            }
        )

    }
}


