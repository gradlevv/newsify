package com.gradlevv.ui.shape

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.gradlevv.core.util.dpf

inline fun View.materialShape(init: MaterialShapeDrawable.() -> Unit): MaterialShapeDrawable {
    return MaterialShapeDrawable().apply(init)
}

inline fun Fragment.materialShape(init: MaterialShapeDrawable.() -> Unit): MaterialShapeDrawable {
    return MaterialShapeDrawable().apply(init)
}

inline fun View.rippleDrawable(init: RippleDrawableBuilder.() -> Unit): RippleDrawable {
    val builder = RippleDrawableBuilder().apply(init)
    return builder.build()
}

data class RippleDrawableBuilder(
    @ColorInt
    var rippleColor: Int = 0x20000000,
    var strokeColor: Int = Color.TRANSPARENT,
    @ColorInt
    var backColor: Int = 0xFFFFFF,
    var drawable: Drawable? = null
) {
    fun build(): RippleDrawable {
        return RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(rippleColor)
            ), MaterialShapeDrawable().apply {
                fillColor = ColorStateList.valueOf(backColor)
                setCornerSize(16.dpf())
                strokeWidth = 1f
                strokeColor = ColorStateList.valueOf(this@RippleDrawableBuilder.strokeColor)
            }, drawable
        )
    }
}