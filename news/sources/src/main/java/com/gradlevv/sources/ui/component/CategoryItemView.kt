package com.gradlevv.sources.ui.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatTextView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.ui.shape.checkableDrawable
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler

class CategoryItemView(context: Context) : AppCompatTextView(context), Checkable {

    private val mWidth = 100.dp()
    private val mHeight = 48.dp()
    private var checked = false


    init {

        gravity = Gravity.CENTER

        setBackgroundColor(Color.BLACK)
        background = checkableDrawable {
            rippleColor = ThemeHandler.getColorWithAlpha(Colors.colorOnBackground70, 10)
            backCornerRadius = 16.dpf()
            normalStrokeColor = ThemeHandler.getColor(Colors.colorTransparent)
            checkedStrokeColor = ThemeHandler.getColor(Colors.colorPrimary)
            checkedColor = ThemeHandler.getColor(Colors.colorPrimary)
            normalColor = ThemeHandler.getColor(Colors.colorStatusBar)
            drawable = materialShape {
                setCornerSize(16.dpf())
            }
        }

        val states = arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            ThemeHandler.getColor(Colors.colorOnBackground70),
            ThemeHandler.getColor(Colors.colorSurface)
        )

        setTextColor(ColorStateList(states, colors))
        refreshDrawableState()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            super.onMeasure(
                MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY)
            )
        } else {
            super.onMeasure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY)
            )
        }
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, intArrayOf(android.R.attr.state_checked))
        }
        return super.onCreateDrawableState(extraSpace)
    }

    override fun setChecked(checked: Boolean) {
        if (this.checked != checked) {
            this.checked = checked
            refreshDrawableState()
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        isChecked = isChecked.not()
    }
}