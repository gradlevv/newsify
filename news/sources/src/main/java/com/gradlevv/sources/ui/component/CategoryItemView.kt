package com.gradlevv.sources.ui.component

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.widget.Checkable
import android.widget.LinearLayout
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.checkableDrawable
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthWrapHeight

class CategoryItemView(context: Context) : LinearLayout(context), Checkable {

    private val tvTitle = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        typeface = Typeface.DEFAULT_BOLD
        gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
    }
    private var checked = false

    init {

        gravity = Gravity.CENTER
        orientation = VERTICAL
        minimumHeight = 56.dp()

        addView(tvTitle, matchWidthWrapHeight())
        setPadding(8.dp(), 0, 8.dp(), 0)
        background = checkableDrawable {
            rippleColor = ThemeHandler.getColorWithAlpha(Colors.colorOnBackground70, 10)
            backCornerRadius = 16.dpf()
            normalStrokeColor = ThemeHandler.getColor(Colors.colorTransparent)
            checkedStrokeColor = ThemeHandler.getColor(Colors.colorPrimary)
            checkedColor = ThemeHandler.getColor(Colors.colorPrimary)
            normalColor = ThemeHandler.getColor(Colors.colorBackground)
            drawable = materialShape {
                setCornerSize(16.dpf())
            }
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

        if (isChecked) {
            tvTitle.setTextColor(ThemeHandler.getColor(Colors.colorBackground))
        } else {
            tvTitle.setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        isChecked = isChecked.not()
    }

    fun setValue(item: CategoryItem) {
        tvTitle.text = context.getString(item.categoryName)
        isChecked = item.isChecked
    }
}