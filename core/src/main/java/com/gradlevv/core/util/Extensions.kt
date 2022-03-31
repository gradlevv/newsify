package com.gradlevv.core.util

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.ceil

fun Int.dpf(): Float {
    return this.dp().toFloat()
}

fun Float.dpf(): Float {
    return this.dp().toFloat()
}

fun Int.dp(): Int {
    return if (this == 0) {
        0
    } else ceil(Resources.getSystem().displayMetrics.density * this).toDouble().toInt()
}

fun Float.dp(): Int {
    return if (this == 0f) {
        0
    } else ceil(Resources.getSystem().displayMetrics.density * this).toDouble().toInt()
}

fun Int.sp(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}