package com.gradlevv.sources.ui.component

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.sources.domain.SourceItemDomainModel
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeManager
import com.gradlevv.ui.utils.wrapWidthAndHeight

class NewsSourceItemView(context: Context) : FrameLayout(context) {

    private val tvTitle = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
    }

    private val tvType = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
    }

    init {

        background = materialShape {
            fillColor = ThemeManager.getColorState(Colors.colorBackground)
            setCornerSize(8.dpf())
            strokeColor = ThemeManager.getColorState(Colors.colorText)
            strokeWidth = 1.dpf()
        }

        addView(tvTitle, wrapWidthAndHeight {
            gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            leftMargin = 8.dp()
        })

        addView(tvType, wrapWidthAndHeight {
            gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
            rightMargin = 8.dp()
        })
    }

    fun setValues(item: SourceItemDomainModel) {
        tvTitle.text = item.name
        tvType.text = item.category
    }
}