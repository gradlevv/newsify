package com.gradlevv.search.ui.component

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import androidx.cardview.widget.CardView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.R
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*

class SearchNewsRowView(context: Context) : CardView(context) {

    private val tvTitle = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
    }

    private val tvDescription = textView {
        setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        maxLines = 3
        ellipsize = TextUtils.TruncateAt.END
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
        text = context.getString(com.gradlevv.search.R.string.search_read_more)
    }

    init {

        radius = 16.dpf()
        elevation = 0f

        addView(linearLayout {

            background = materialShape {
                fillColor = ThemeHandler.getColorState(Colors.colorBackground)
            }

            orientation = VERTICAL

            background = materialShape {
                fillColor = ThemeHandler.getColorState(Colors.colorWhite)
            }

            addView(ivNews, matchWidthCustomHeight(128))

            addView(tvTitle, matchWidthWrapHeight {
                topMargin = 8.dp()
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })

            addView(tvDescription, matchWidthWrapHeight {
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })


            addView(frameLayout {
                addView(tvReadMore, matchWidthWrapHeight {
                    rightMargin = 24.dp()
                    gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                })

                addView(ivReadMore, wrapWidthAndHeight {
                    gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                })
            }, matchWidthWrapHeight {
                rightMargin = 8.dp()
                leftMargin = 8.dp()
                topMargin = 28.dp()
                bottomMargin = 20.dp()
            })

        }, matchWidthAndHeight())

    }

    fun bind(item: SearchNewsItem) {

        tvTitle.text = item.title
        tvDescription.text = item.description

        ivNews.loadImage(item.imageUrl)
    }
}