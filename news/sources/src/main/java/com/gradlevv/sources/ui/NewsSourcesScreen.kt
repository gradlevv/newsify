package com.gradlevv.sources.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Composable
fun NewsSourcesScreen() {
    Text("NewsSourcesScreen")
}

@Serializable
data object NewsSourcesDestination