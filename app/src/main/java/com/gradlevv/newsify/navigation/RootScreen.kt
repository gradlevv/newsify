package com.gradlevv.newsify.navigation

sealed class RootScreen(val route: Route) {
    object Home : RootScreen(HOME_NESTED_GRAPH)
    object Sources : RootScreen(SOURCES_NESTED_GRAPH)
    object Search : RootScreen(SEARCH_NESTED_GRAPH)
    object Settings : RootScreen(SETTING_NESTED_GRAPH)
}

val HOME_NESTED_GRAPH = Route("")
val SOURCES_NESTED_GRAPH = Route("")
val SEARCH_NESTED_GRAPH = Route("")
val SETTING_NESTED_GRAPH = Route("")