package com.gradlevv.core.util

import android.R
import android.app.Activity
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.DrawableRes

fun Activity.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return applicationContext.getCompatDrawable(id)
}

fun Activity.getSelectorDrawable(
    @DrawableRes checkedDrawableRes: Int,
    @DrawableRes unCheckedDrawableRes: Int
): Drawable {
    val drawable = StateListDrawable()
    drawable.addState(
        intArrayOf(R.attr.state_checked),
        getCompatDrawable(checkedDrawableRes)
    )
    drawable.addState(
        intArrayOf(-R.attr.state_checked),
        getCompatDrawable(unCheckedDrawableRes)
    )

    return drawable
}