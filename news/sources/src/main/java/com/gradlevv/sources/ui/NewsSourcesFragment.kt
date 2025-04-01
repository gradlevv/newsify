package com.gradlevv.sources.ui

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.dp
import com.gradlevv.newsify.ui.R
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthAndHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import com.gradlevv.ui.utils.setCompatDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsSourcesFragment : BaseFragment<NewsSourcesViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvSourceList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var tvToolbar: TextView
    private lateinit var rvCategories: RecyclerView
    private lateinit var horizontalLayoutManager: LinearLayoutManager
    private lateinit var ivLogo: ImageView

    private val newsSourcesAdapter: NewsSourcesAdapter by lazy {
        NewsSourcesAdapter(
            ::onItemClick
        )
    }

    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(
            ::onCategoryClick
        )
    }

    private fun onCategoryClick(position: Int, categoryItem: CategoryItem) {
        viewModel.categoryChangeClick(getString(categoryItem.type))
    }

    private fun onItemClick(position: Int, item: SourceItem) {

        NewsSourceBottomSheet(requireContext())
            .setOnSubmitClickListener {
                intentUtils.openLinkInDeviceBrowser(item.url)
            }.setValues(item).show()

    }

    @Inject
    lateinit var intentUtils: IntentUtils

    override val viewModel: NewsSourcesViewModel by viewModels()

    override fun initLayout(): View? {

        root = frameLayout {

            loading = ProgressBar(context).apply {
                isGone = true
            }

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                typeface = Typeface.DEFAULT_BOLD
                text = getString(com.gradlevv.newsify.news.sources.R.string.top_news_source_title)
                gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            }

            linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            rvSourceList = recyclerView {
                layoutManager = linearLayoutManager
                clipToPadding = false
                adapter = newsSourcesAdapter
                setPadding(0, 0, 0, 80.dp())
            }

            horizontalLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            rvCategories = recyclerView {
                layoutManager = horizontalLayoutManager
                adapter = categoriesAdapter
            }

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })
            addView(ScrollView(context).apply {

                addView(linearLayout {

                    orientation = LinearLayout.VERTICAL

                    addView(ivLogo, matchWidthWrapHeight {
                        topMargin = 24.dp()
                    })

                    addView(tvToolbar, matchWidthWrapHeight {
                        topMargin = 24.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })

                    addView(rvCategories, matchWidthWrapHeight {
                        topMargin = 8.dp()
                        leftMargin = 8.dp()
                    })
                    addView(rvSourceList, matchWidthAndHeight {
                        topMargin = 8.dp()
                        rightMargin = 8.dp()
                        leftMargin = 8.dp()
                    })

                }, matchWidthAndHeight())

            })
        }

        return root
    }

    override fun setUpUi() {

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.sourceList.collect { topHeadLinesState ->

                        if (topHeadLinesState.isLoading) {
                            loading.visibility = View.VISIBLE
                            return@collect
                        }

                        if (topHeadLinesState.isError) {
                            loading.visibility = View.GONE
                            return@collect
                        }

                        loading.visibility = View.GONE
                        newsSourcesAdapter.submitList(topHeadLinesState.items)
                        return@collect

                    }
                }

                launch {
                    viewModel.categoryList.collect {
                        categoriesAdapter.submitList(it)
                    }
                }
            }

        }

    }

    override fun daggerConfiguration() {
    }
}