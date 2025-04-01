package com.gradlevv.list.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.dp
import com.gradlevv.newsify.ui.R
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.normalButton
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvContent: TextView
    private lateinit var tvNewsDate: TextView
    private lateinit var ivNewsImage: ImageView
    private lateinit var btnMoreInfo: MaterialButton
    private lateinit var tvToolbar: TextView

    @Inject
    lateinit var intentUtils: IntentUtils

    override val viewModel: NewsListViewModel by hiltNavGraphViewModels(R.id.main_navigation)

    override fun initLayout(): View? {
        root = linearLayout {
            orientation = LinearLayout.VERTICAL

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorWhite))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = getString(com.gradlevv.newsify.news.list.R.string.top_news_detail_title)
                gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                }
                setPadding(0, 12.dp(), 0, 12.dp())
            }

            tvTitle = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorText))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            }
            tvContent = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorText))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            }
            tvNewsDate = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorText))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            }
            ivNewsImage = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            btnMoreInfo = normalButton {
                text = context.getString(com.gradlevv.newsify.news.list.R.string.more_info)
                insetTop = 0
                insetBottom = 0
                setTextColor(ThemeHandler.getColor(Colors.colorBackground))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                cornerRadius = 14.dp()
                backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        ThemeHandler.getColor(
                            Colors.colorText
                        )
                    )
            }

            addView(tvToolbar, matchWidthWrapHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            })

            addView(ivNewsImage, matchWidthAndCustomHeight(300))

            addView(tvTitle, matchWidthWrapHeight {
                topMargin = 24.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(tvNewsDate, matchWidthWrapHeight {
                topMargin = 24.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(tvContent, matchWidthWrapHeight {
                topMargin = 24.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()

            })

            addView(btnMoreInfo,matchWidthWrapHeight{
                topMargin = 40.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })
        }
        return root
    }

    override fun setUpUi() {
        lifecycleScope.launchWhenCreated {
            viewModel.newsDetailItem.collect { item ->
                item?.let {
                    tvTitle.text = it.title
                    tvNewsDate.text = it.publishedAt
                    tvContent.text = it.content
                    ivNewsImage.loadImage(it.imageUrl)

                    btnMoreInfo.setOnClickListener {
                        intentUtils.openLinkInDeviceBrowser(item.url)
                    }
                }
            }
        }
    }

    override fun daggerConfiguration() {
    }
}