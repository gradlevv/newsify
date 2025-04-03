package com.gradlevv.newsify.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.gradlevv.newsify.R
import com.gradlevv.ui.utils.NewsifyIcons

enum class TopLevelScreen(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = NewsifyIcons.Home,
        unselectedIcon = NewsifyIcons.HomeUnselected,
        iconTextId = R.string.home,
        titleTextId = R.string.home
    ),
    SOURCES(
        selectedIcon = NewsifyIcons.Sources,
        unselectedIcon = NewsifyIcons.SourcesUnselected,
        iconTextId = R.string.sources,
        titleTextId = R.string.sources
    ),
    SEARCH(
        selectedIcon = NewsifyIcons.Search,
        unselectedIcon = NewsifyIcons.SearchUnselected,
        iconTextId = R.string.search,
        titleTextId = R.string.search
    ),
    SETTING(
        selectedIcon = NewsifyIcons.Setting,
        unselectedIcon = NewsifyIcons.SettingUnselected,
        iconTextId = R.string.setting,
        titleTextId = R.string.setting
    ),
}