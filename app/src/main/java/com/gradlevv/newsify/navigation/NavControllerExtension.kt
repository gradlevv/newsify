package com.gradlevv.newsify.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.gradlevv.list.ui.NewsListDestination
import com.gradlevv.search.ui.SearchNewsDestination
import com.gradlevv.setting.ui.SettingDestination
import com.gradlevv.sources.ui.NewsSourcesDestination


fun NavBackStackEntry?.isBottomBarVisible(destinations: Set<String?>): Boolean {
    val hierarchyRoutes = this?.destination
        ?.hierarchy
        ?.mapNotNull { it.route }
        ?.toSet() ?: return false
    return destinations.any { it in hierarchyRoutes }
}

val bottomBarRoutes = setOf(
    NewsListDestination::class.qualifiedName,
    SearchNewsDestination::class.qualifiedName,
    NewsSourcesDestination::class.qualifiedName,
    SettingDestination::class.qualifiedName
)