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
import com.gradlevv.newsify.ui.R
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class TopHeadLineRowView(context: Context) : CardView(context) {

    private val tvTitle = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
    }

    private val tvPublishDate = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground50))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
    }

    private val tvAuthor = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
    }

    private val ivNews = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    private val ivReadMore = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setCompatDrawable(R.drawable.ic_arrow_right)
    }

    private val tvReadMore = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorPrimary))
        gravity = Gravity.RIGHT
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        text = context.getString(com.gradlevv.newsify.news.list.R.string.news_list_read_more)
    }

    private val ivDate = imageView {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setCompatDrawable(R.drawable.ic_clock)
    }

    init {

        radius = 16.dpf()
        elevation = 0f

        addView(linearLayout {

            background = materialShape {
                fillColor = ThemeHandler.getColorState(Colors.colorWhite)
            }

            orientation = VERTICAL

            addView(ivNews, matchWidthCustomHeight(180))

            addView(tvAuthor, matchWidthWrapHeight {
                topMargin = 16.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(tvTitle, matchWidthWrapHeight {
                topMargin = 12.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(frameLayout {

                addView(ivDate, wrapWidthAndHeight {
                    gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                })

                addView(tvPublishDate, matchWidthWrapHeight {
                    gravity = Gravity.LEFT or Gravity.CENTER
                    leftMargin = 24.dp()
                })

                addView(tvReadMore, matchWidthWrapHeight {
                    rightMargin = 24.dp()
                    gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                })

                addView(ivReadMore, wrapWidthAndHeight {
                    gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                })

            }, matchWidthWrapHeight {
                topMargin = 32.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
                bottomMargin = 24.dp()
            })

        }, matchWidthWrapHeight())

    }

    fun bind(item: TopHeadLinesItem) {

        tvTitle.text = item.title
        tvPublishDate.text = item.publishedAt
        tvAuthor.text = item.author

        ivNews.loadImage(item.imageUrl)
    }
}