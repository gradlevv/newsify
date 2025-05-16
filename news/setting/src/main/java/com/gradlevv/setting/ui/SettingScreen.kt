package com.gradlevv.setting.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Composable
fun SettingScreen() {
    Text("SettingScreen")
}

@Serializable
data object SettingDestination