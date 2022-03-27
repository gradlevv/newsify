package com.gradlevv.list.ui

import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.utils.matchWidthAndHeight
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsListFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

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
        root = linearLayout {

            orientation = LinearLayout.VERTICAL

            gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

            rvTopHeadLines = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = topHeadLinesAdapter
            }

            addView(rvTopHeadLines,matchWidthAndHeight())
        }
        return root
    }

    override fun setUpUi() {

        lifecycleScope.launchWhenCreated {
            viewModel.topHeadLinesList.collect { topHeadLinesState ->

                if (topHeadLinesState.isLoading) {

                }

                if (topHeadLinesState.items.isNotEmpty()) {
                    topHeadLinesAdapter.submitList(topHeadLinesState.items)
                }

                if (topHeadLinesState.isError) {

                }
            }
        }
    }

    override fun daggerSetUp() {
        DaggerNewsListComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}