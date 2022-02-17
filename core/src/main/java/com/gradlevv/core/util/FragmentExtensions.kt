package com.gradlevv.core.util

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return requireContext().getCompatDrawable(id)
}

fun Fragment.getSelectorDrawable(
    @DrawableRes checkedDrawableRes: Int,
    @DrawableRes unCheckedDrawableRes: Int
): Drawable {
    val drawable = StateListDrawable()
    drawable.addState(
        intArrayOf(android.R.attr.state_checked),
        getCompatDrawable(checkedDrawableRes)
    )
    drawable.addState(
        intArrayOf(-android.R.attr.state_checked),
        getCompatDrawable(unCheckedDrawableRes)
    )

    return drawable
}