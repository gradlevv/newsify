package com.gradlevv.search.ui.component

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import androidx.cardview.widget.CardView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class SearchNewsRowView(context: Context) : CardView(context) {

    private val tvTitle = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
    }

    private val tvPublishDate = textView {
        setTextColor(ThemeManager.getColor(Colors.colorText))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
    }

    private val tvAuthor = textView {
        setTextColor(ThemeManager.getColor(Colors.colorWhite))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        background = materialShape {
            setPadding(8.dp(),8.dp(),8.dp(),8.dp())
            fillColor = ThemeManager.getColorStateWithAlpha(Colors.colorPrimary,15)
        }
    }

    private val ivNews = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP

    }

    init {

        radius = 12.dpf()
        elevation = 2.dpf()

        background = materialShape {
            fillColor = ThemeManager.getColorState(Colors.colorBackground)
        }

        addView(linearLayout {

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

        },matchWidthAndHeight())

    }

    fun bind(item: SearchNewsItem) {

        tvTitle.text = item.title
        tvPublishDate.text = item.publishedAt
        tvAuthor.text = item.author

        ivNews.loadImage(item.imageUrl)
    }
}