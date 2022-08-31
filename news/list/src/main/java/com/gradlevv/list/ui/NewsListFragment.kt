package com.gradlevv.list.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.list.R
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthAndHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var gridLayoutManager: LinearLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var tvToolbar: TextView

    private val topHeadLinesAdapter: TopHeadLinesAdapter by lazy {
        TopHeadLinesAdapter(
            ::onItemClick
        )
    }

    private fun onItemClick(position: Int, topHeadLinesItem: TopHeadLinesItem) {
        viewModel.navigateToNewsDetail(topHeadLinesItem)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsListViewModel by navGraphViewModels(R.id.main_navigation) { viewModelFactory }

    override fun initLayout(): View? {
        root = frameLayout {

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorWhite))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = getString(R.string.top_head_lines_title)
                gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                }
                setPadding(0, 12.dp(), 0, 12.dp())
            }

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            gridLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            rvTopHeadLines = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = topHeadLinesAdapter
                setPadding(0, 0, 0, 80.dp())
            }

            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })
            addView(rvTopHeadLines, matchWidthAndHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                topMargin = 42.dp()
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })
            addView(tvToolbar, matchWidthWrapHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
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
                }

                if (topHeadLinesState.items.isNotEmpty()) {
                    loading.visibility = View.GONE
                    topHeadLinesAdapter.submitList(topHeadLinesState.items)
                }

                if (topHeadLinesState.isError) {
                    loading.visibility = View.GONE
                }
            }
        }
    }

    override fun daggerConfiguration() {
        DaggerNewsListComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}