package com.gradlevv.setting.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.settingScreen() {
    composable<SettingDestination> {
        SettingScreen()
    }
}