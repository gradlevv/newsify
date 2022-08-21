package com.gradlevv.list.ui.component

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import androidx.cardview.widget.CardView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class TopHeadLineRowView(context: Context) : CardView(context) {

    private val tvTitle = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
    }

    private val tvPublishDate = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
    }

    private val tvAuthor = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorWhite))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        background = materialShape {
            setPadding(8.dp(),8.dp(),8.dp(),8.dp())
            fillColor = ThemeHandler.getColorStateWithAlpha(Colors.colorPrimary,15)
        }
    }

    private val ivNews = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP

    }

    init {

        radius = 12.dpf()
        elevation = 2.dpf()

        addView(linearLayout {

            background = materialShape {
                fillColor = ThemeHandler.getColorState(Colors.colorBackground)
            }

            orientation = VERTICAL

            addView(frameLayout {

                addView(ivNews, matchWidthCustomHeight(180))

                addView(tvAuthor, matchWidthWrapHeight {
                    gravity = Gravity.BOTTOM or Gravity.LEFT
                })

            },matchWidthAndWrapHeight())

            addView(tvTitle, matchWidthWrapHeight {
                topMargin = 16.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(tvPublishDate, matchWidthWrapHeight {
                topMargin = 16.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
                bottomMargin = 16.dp()
            })

        },matchWidthWrapHeight())

    }

    fun bind(item: TopHeadLinesItem) {

        tvTitle.text = item.title
        tvPublishDate.text = item.publishedAt
        tvAuthor.text = item.author

        ivNews.loadImage(item.imageUrl)
    }
}