package com.gradlevv.search.ui

import android.text.InputFilter
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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
import com.gradlevv.search.di.DaggerSearchNewsComponent
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.editText
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.utils.matchWidthAndHeight
import com.gradlevv.ui.utils.matchWidthCustomHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SearchNewsFragment : BaseFragment<SearchNewsViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var etSearch: EditText

    private val searchNewsAdapter: SearchNewsAdapter by lazy {
        SearchNewsAdapter(
            ::onItemClick
        )
    }

    private fun onItemClick(position: Int, topHeadLinesItem: SearchNewsItem) {

    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: SearchNewsViewModel by viewModels { viewModelFactory }

    override fun createUi(): View? {
        root = frameLayout {

            etSearch = editText {
                gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
                setImeActionLabel("Next", EditorInfo.IME_ACTION_NEXT)
                setTextSize(TypedValue.COMPLEX_UNIT_SP,14f)
                maxLines = 1
                filters = arrayOf(InputFilter.LengthFilter(16))

            }

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

            rvTopHeadLines = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = searchNewsAdapter
                setPadding(0, 0, 0, 80.dp())
            }
            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })

            addView(etSearch, matchWidthCustomHeight(48) {
                gravity = Gravity.TOP
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })

            addView(rvTopHeadLines, matchWidthAndHeight {
                gravity = Gravity.TOP
                topMargin = 40.dp()
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })
        }
        return root
    }

    override fun setUpUi() {

        etSearch.requestFocus()

        lifecycleScope.launchWhenCreated {
            viewModel.searchNewsList.collect { searchList ->

                if (searchList.isLoading) {
                    loading.visibility = View.VISIBLE
                }

                if (searchList.items.isNotEmpty()) {
                    loading.visibility = View.GONE
                    searchNewsAdapter.submitList(searchList.items)
                }

                if (searchList.isError) {
                    loading.visibility = View.GONE
                }
            }
        }
    }

    override fun daggerSetUp() {
        DaggerSearchNewsComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}