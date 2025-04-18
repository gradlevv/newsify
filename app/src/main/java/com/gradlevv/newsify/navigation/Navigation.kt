package com.gradlevv.newsify.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun NewsifyNavigationBar(
    destinations: List<TopLevelScreen>,
    onNavigationSelected: (TopLevelScreen) -> Unit,
    modifier: Modifier
) {
    NavigationBar(modifier = modifier) {

        destinations.forEach { current ->
            NavigationBarItem(
                selected = false,
                onClick = { onNavigationSelected(current) },
                icon = {
                    Icon(
                        imageVector = current.selectedIcon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = stringResource(current.titleTextId)
                    )
                }

            )
        }

    }
}


