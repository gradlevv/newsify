package com.samcrow.core.util

import androidx.annotation.StringRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationCommand {

    data class To(val direction: NavDirections) : NavigationCommand()
    data class ToDeppLink(@StringRes val deepLink: Int, val navOptions: NavOptions? = null) : NavigationCommand()
    object Back : NavigationCommand()

}