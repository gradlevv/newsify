package com.gradlevv.sources.ui.component

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.gradlevv.sources.domain.CategoryItem
import com.gradlevv.ui.utils.wrapWidthAndHeight

class CategoryItemView(context: Context) : FrameLayout(context) {

    private val radioThumb = RadioThumb(context)

    init {
        addView(radioThumb, wrapWidthAndHeight {
            gravity = Gravity.CENTER
        })
    }

    fun setValue(categoryItem: CategoryItem) {
        radioThumb.text = context.getString(categoryItem.categoryName)
    }

}