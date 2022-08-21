package com.gradlevv.core.util

import androidx.annotation.StringRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationModel {

    data class To(val direction: NavDirections) : NavigationModel()
    data class ToDeppLink(@StringRes val deepLink: Int, val navOptions: NavOptions? = null) : NavigationModel()
    object Back : NavigationModel()

}