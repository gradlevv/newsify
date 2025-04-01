package com.gradlevv.core.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.gradlevv.core.CoreComponentProvider


@ColorInt
fun Context.getColorInt(@ColorRes color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return resources.getColor(color, theme)
    } else resources.getColor(color)
}

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return try {
        AppCompatResources.getDrawable(this, id)
    } catch (e: Exception) {
        null
    }
}

fun Activity.setSystemBarsColor(
    @ColorInt statusBarColor: Int,
    @ColorInt navigationBarColor: Int,
    isDark: Boolean = false
) {
    window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window?.statusBarColor = statusBarColor

    window?.navigationBarColor = navigationBarColor

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val flag = if (isDark)
            0
        else
            (WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
        window?.insetsController?.setSystemBarsAppearance(
            flag,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val flag = if (isDark)
            0
        else
            @Suppress("DEPRECATION")
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = flag

    }

}
