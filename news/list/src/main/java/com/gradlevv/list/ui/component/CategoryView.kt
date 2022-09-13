package com.gradlevv.list.ui.component

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.utils.*

class CategoryView(context: Context) : LinearLayout(context) {

    private var tvCategory: TextView = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
    }
    private var ivCategory: ImageView = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    init {

        orientation = VERTICAL

        addView(CardView(context).apply {
            radius = 16.dpf()
            addView(ivCategory, customWidthAndHeight(96, 96))
        })

        addView(tvCategory, matchWidthWrapHeight {
            topMargin = 4.dp()
            bottomMargin = 4.dp()
        })

    }

    fun bind(item: CategoryItem) {
        tvCategory.text = context.getString(item.categoryName)
        ivCategory.setCompatDrawable(item.icon)
    }
}