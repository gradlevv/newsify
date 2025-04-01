package com.gradlevv.list.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.newsify.ui.R
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import com.gradlevv.ui.utils.setCompatDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var rvCategories: RecyclerView
    private lateinit var gridLayoutManager: LinearLayoutManager
    private lateinit var horizontalLayoutManager: LinearLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var ivLogo: ImageView
    private lateinit var tvCategories: TextView
    private lateinit var tvTopNews: TextView

    private val topHeadLinesAdapter: TopHeadLinesAdapter by lazy {
        TopHeadLinesAdapter(
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

    private fun onItemClick(position: Int, topHeadLinesItem: TopHeadLinesItem) {
        viewModel.navigateToNewsDetail(topHeadLinesItem)
    }

    override val viewModel: NewsListViewModel by navGraphViewModels(R.id.main_navigation)

    override fun initLayout(): View? {

        root = linearLayout {

            orientation = LinearLayout.VERTICAL

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            tvCategories = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                text = getString(com.gradlevv.newsify.news.list.R.string.news_list_categories)
                gravity = Gravity.LEFT or Gravity.CENTER_HORIZONTAL
            }

            tvTopNews = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                text = getString(com.gradlevv.newsify.news.list.R.string.news_list_lines_title)
                gravity = Gravity.LEFT or Gravity.CENTER_HORIZONTAL
            }

            gridLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            horizontalLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            rvCategories = recyclerView {
                layoutManager = horizontalLayoutManager
                adapter = categoriesAdapter
            }

            rvTopHeadLines = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = topHeadLinesAdapter
                setPadding(0, 0, 0, 80.dp())
            }

            addView(ivLogo, matchWidthWrapHeight {
                topMargin = 32.dp()
            })

            addView(ScrollView(context).apply {

                addView(linearLayout {

                    orientation = LinearLayout.VERTICAL

                    addView(tvCategories, matchWidthWrapHeight {
                        topMargin = 48.dp()
                        leftMargin = 16.dp()
                    })

                    addView(rvCategories, matchWidthWrapHeight {
                        topMargin = 8.dp()
                        leftMargin = 8.dp()
                        rightMargin = 8.dp()
                    })

                    addView(tvTopNews, matchWidthWrapHeight {
                        topMargin = 32.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })

                    addView(frameLayout {
                        addView(loading, matchWidthWrapHeight())
                        addView(rvTopHeadLines, matchWidthHeight())
                    }, matchWidthHeight {
                        topMargin = 12.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })
                }, matchWidthHeight())

            })

        }
        return root
    }

    override fun setUpUi() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.topHeadLinesList.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { topHeadLinesState ->

                if (topHeadLinesState.isLoading) {
                    loading.visibility = View.VISIBLE
                    return@collect
                }

                if (topHeadLinesState.isError) {
                    loading.visibility = View.GONE
                    return@collect
                }

                if (topHeadLinesState.items.isNotEmpty()) {
                    loading.visibility = View.GONE
                    topHeadLinesAdapter.submitList(topHeadLinesState.items)
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categoryList.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect {
                categoriesAdapter.submitList(it)
            }

        }
    }

    override fun onThemeChanged() {
    }

    override fun daggerConfiguration() {
    }
}