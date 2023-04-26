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

inline fun View.checkableDrawable(init: CheckableRippleDrawableBuilder.() -> Unit): Drawable {
    val builder = CheckableRippleDrawableBuilder().apply(init)
    return builder.build()
}

data class CheckableRippleDrawableBuilder(
    @ColorInt
    var rippleColor: Int = 0x20000000,
    var backCornerRadius: Float = 0f,
    var drawable: Drawable? = null,
    @ColorInt
    var normalStrokeColor: Int = 0,
    var strokeWidth: Float = 0f,
    @ColorInt
    var checkedStrokeColor: Int = 0,
    @ColorInt
    var checkedColor: Int = 0,
    @ColorInt
    var normalColor: Int = 0
) {

    fun build(): Drawable {
        return RippleDrawable(
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(rippleColor)
            ), MaterialShapeDrawable().apply {

                val states = arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                )

                val colors = intArrayOf(
                    normalColor,
                    checkedColor
                )

                val strokeColors = intArrayOf(
                    normalStrokeColor,
                    checkedStrokeColor
                )

                tintList = ColorStateList(states, colors)
                strokeWidth = this@CheckableRippleDrawableBuilder.strokeWidth
                strokeColor = ColorStateList(states, strokeColors)
                setCornerSize(backCornerRadius)

            }, drawable
        )
    }

}