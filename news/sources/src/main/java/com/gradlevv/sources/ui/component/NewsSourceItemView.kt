package com.gradlevv.sources.ui.component

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.sources.R
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class NewsSourceItemView(context: Context) : FrameLayout(context) {

    private val tvTitle = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
    }

    private val ivArrow = imageView {
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        setCompatDrawable(R.drawable.ic_arrow_right)
    }

    private val ivLogo = imageView {
        scaleType = ImageView.ScaleType.CENTER_INSIDE
        setCompatDrawable(R.drawable.ic_global_stroke)
        setColorFilter(ThemeHandler.getColor(Colors.colorOnBackground50))
    }

    init {

        background = materialShape {
            fillColor = ThemeHandler.getColorState(Colors.colorSurface)
            setCornerSize(8.dpf())
        }


        addView(ivLogo,customWithAndHeight(24,24){
            gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            leftMargin = 16.dp()
        })

        addView(tvTitle, wrapWidthAndHeight {
            gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            leftMargin = 52.dp()
        })

        addView(ivArrow, customWithAndHeight(24,24) {
            gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
            rightMargin = 16.dp()
        })
    }

    fun setValues(item: SourceItem) {
        tvTitle.text = item.name
    }
}