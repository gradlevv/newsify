package com.gradlevv.setting.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.settingScreen() {
    composable<SettingDestination> {
        SettingScreen()
    }
}

fun NavController.navigateToSettingScreen() {
    navigate(SettingDestination)
}