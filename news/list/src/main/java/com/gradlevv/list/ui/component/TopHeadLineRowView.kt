package com.gradlevv.list.ui.component

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class TopHeadLineRowView(context: Context) : FrameLayout(context) {

    private val tvTitle = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
    }

    private val tvDescription = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
    }

    private val tvContent = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
    }

    private val ivLogo = imageView {
        scaleType = ImageView.ScaleType.CENTER_INSIDE
    }

    init {

        background = materialShape {
            fillColor = ThemeManager.getColorState(Colors.colorBackground)
            strokeWidth = 1.dpf()
            strokeColor = ThemeManager.getColorState(Colors.colorPrimaryDark)
            setCornerSize(14.dpf())
        }

        addView(linearLayout {

            orientation = LinearLayout.VERTICAL

            addView(tvTitle, matchWidthAndHeight {
                topMargin = 32.dp()
            })

            addView(tvDescription, matchWidthAndHeight {
                topMargin = 16.dp()
            })

        }, matchWidthAndHeight {
            rightMargin = 16.dp()
            leftMargin = 16.dp()
            gravity = Gravity.CENTER or Gravity.TOP
        })

        addView(linearLayout {
            orientation = LinearLayout.HORIZONTAL

            addView(ivLogo, customWidthAndHeight(36, 36))
            addView(tvContent, wrapWidthAndHeight {
                leftMargin = 4.dp()
            })

        }, matchWidthWrapHeight {
            gravity = Gravity.BOTTOM or Gravity.LEFT
            rightMargin = 16.dp()
            leftMargin = 16.dp()
        })

    }

    fun bind(item: TopHeadLinesItem) {

        tvTitle.text = item.title
        tvDescription.text = item.description
        tvContent.text = item.author

        ivLogo.loadImage(item.imageUrl)
    }
}