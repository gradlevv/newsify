package com.gradlevv.search.ui

import android.content.res.ColorStateList
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.core.util.getCompatDrawable
import com.gradlevv.newsify.ui.R
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.normalButton
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.customWidthAndHeight
import com.gradlevv.ui.utils.loadImage
import com.gradlevv.ui.utils.matchWidthAndCustomHeight
import com.gradlevv.ui.utils.matchWidthHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import com.gradlevv.ui.utils.setCompatDrawable
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.gradlevv.ui.utils.wrapWidthAndHeight
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<SearchNewsViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvContent: TextView
    private lateinit var ivNewsImage: ImageView
    private lateinit var btnMoreInfo: TextView
    private lateinit var ivLogo: ImageView
    private lateinit var ivBack: ImageView
    private lateinit var ivDate: ImageView
    private lateinit var tvDate: TextView
    private lateinit var tvAuthor: TextView

    @Inject
    lateinit var intentUtils: IntentUtils

    override val viewModel: SearchNewsViewModel by hiltNavGraphViewModels(R.id.main_navigation)

    override fun initLayout(): View? {
        root = linearLayout {

            orientation = LinearLayout.VERTICAL

            ivDate = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setCompatDrawable(R.drawable.ic_clock)
            }

            ivBack = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setCompatDrawable(R.drawable.ic_back)
            }

            tvAuthor = textView {
                gravity = Gravity.RIGHT
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground50))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                ellipsize = TextUtils.TruncateAt.END
            }

            tvDate = textView {
                gravity = Gravity.LEFT
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground50))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            }

            tvTitle = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            }
            tvContent = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }

            ivNewsImage = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            btnMoreInfo = normalButton {
                setTextColor(ThemeHandler.getColor(Colors.colorPrimary))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)

                icon = getCompatDrawable(R.drawable.ic_link_14)
                iconTint = ColorStateList.valueOf(ThemeHandler.getColor(Colors.colorPrimary))
                cornerRadius = 10.dp()
                text = getString(com.gradlevv.newsify.news.search.R.string.search_read_full_article)
                backgroundTintList =
                    ColorStateList.valueOf(
                        ThemeHandler.getColor(
                            Colors.colorPrimaryBackground
                        )
                    )
            }

            addView(frameLayout {
                addView(ivLogo, matchWidthWrapHeight {
                    topMargin = 16.dp()
                })
                addView(ivBack, wrapWidthAndHeight {
                    gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                    leftMargin = 16.dp()
                })
            })

            addView(CardView(context).apply {

                radius = 16.dpf()
                elevation = 0f

                addView(linearLayout {

                    background = materialShape {
                        fillColor = ThemeHandler.getColorState(Colors.colorSurface)
                    }

                    orientation = LinearLayout.VERTICAL

                    addView(ivNewsImage, matchWidthAndCustomHeight(194))

                    addView(tvTitle, matchWidthWrapHeight {
                        topMargin = 16.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })


                    addView(tvContent, matchWidthWrapHeight {
                        topMargin = 8.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()

                    })

                    addView(btnMoreInfo, customWidthAndHeight(142, 52) {
                        gravity = Gravity.CENTER
                        topMargin = 32.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })

                    addView(View(context).apply {
                        setBackgroundColor(ThemeHandler.getColor(Colors.colorBackground))
                    }, matchWidthAndCustomHeight(1) {
                        topMargin = 32.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })

                    addView(frameLayout {

                        addView(ivDate, wrapWidthAndHeight {
                            gravity = Gravity.CENTER or Gravity.LEFT
                            leftMargin = 16.dp()
                        })

                        addView(tvDate, matchWidthWrapHeight {
                            leftMargin = 40.dp()
                        })

                        addView(tvAuthor, matchWidthWrapHeight {
                            rightMargin = 16.dp()
                        })

                    }, matchWidthWrapHeight {
                        topMargin = 16.dp()
                        bottomMargin = 16.dp()
                    })

                }, matchWidthHeight())

            }, matchWidthWrapHeight {
                topMargin = 24.dp()
            })

        }
        return root
    }

    override fun setUpUi() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsDetailItem.flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { item ->
                item?.let {
                    ivNewsImage.loadImage(item.imageUrl)
                    tvContent.text = item.content
                    tvTitle.text = item.title
                    tvDate.text = item.publishedAt
                    tvAuthor.text = item.author

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