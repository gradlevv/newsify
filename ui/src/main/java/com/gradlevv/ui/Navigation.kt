package com.gradlevv.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gradlevv.ui.theme.ColorStatusBar

@Composable
fun RowScope.NeNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon
    )
}

@Composable
fun NeNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = NeNavigationDefaults.navigationContentColor(),
        content = content
    )
}

object NeNavigationDefaults {
    @Composable
    fun navigationContentColor() = ColorStatusBar
}

