package com.gradlevv.core.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.gradlevv.core.CoreComponentProvider
import com.gradlevv.core.di.CoreComponent
import java.lang.Exception
import java.lang.IllegalArgumentException



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

fun Context.coreComponent(): CoreComponent =
    if (applicationContext is CoreComponentProvider) {
        (applicationContext as CoreComponentProvider).coreComponent()
    }else{
        throw IllegalArgumentException("Application class must implement CoreComponentProvider")
    }
