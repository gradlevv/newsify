package com.gradlevv.list.ui

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.utils.matchWidthAndHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsListFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var loading: ProgressBar

    private val topHeadLinesAdapter: TopHeadLinesAdapter by lazy {
        TopHeadLinesAdapter(
            ::onItemClick
        )
    }

    private fun onItemClick(position: Int, topHeadLinesItem: TopHeadLinesItem) {

    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsListViewModel by viewModels { viewModelFactory }

    override fun createUi(): View? {
        root = frameLayout {

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

            rvTopHeadLines = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = topHeadLinesAdapter
                setPadding(0,0,0,80.dp())
            }
            addView(loading,matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })
            addView(rvTopHeadLines,matchWidthAndHeight{
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })
        }
        return root
    }

    override fun setUpUi() {

        lifecycleScope.launchWhenCreated {
            viewModel.topHeadLinesList.collect { topHeadLinesState ->

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

    override fun daggerSetUp() {
        DaggerNewsListComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}