package com.gradlevv.list.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.list.R
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.*
import com.gradlevv.ui.utils.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsListViewModel by navGraphViewModels(R.id.main_navigation) { viewModelFactory }

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
                text = getString(R.string.news_list_categories)
                gravity = Gravity.LEFT or Gravity.CENTER_HORIZONTAL
            }

            tvTopNews = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                text = getString(R.string.news_list_lines_title)
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

            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })

            addView(tvCategories, matchWidthWrapHeight {
                topMargin = 48.dp()
                rightMargin = 16.dp()
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

            addView(rvTopHeadLines, matchWidthHeight {
                topMargin = 12.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })
        }
        return ScrollView(context).apply {
            addView(root)
        }
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
        DaggerNewsListComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}