package com.gradlevv.search.ui

import android.content.res.ColorStateList
import android.text.InputFilter
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.search.R
import com.gradlevv.search.di.DaggerSearchNewsComponent
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.*
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SearchNewsFragment : BaseFragment<SearchNewsViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvTopHeadLines: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var etSearch: EditText
    private lateinit var flSearch: FrameLayout
    private lateinit var ivSearch: ImageView
    private lateinit var btnSearch: MaterialButton
    private lateinit var llSearch: LinearLayout
    private lateinit var tvNoResult: TextView
    private lateinit var tvToolbar: TextView

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

    override fun initLayout(): View? {

        root = frameLayout {

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorWhite))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = getString(R.string.top_news_search_title)
                gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                }
                setPadding(0, 12.dp(), 0, 12.dp())
            }

            etSearch = editText {
                gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                setImeActionLabel("Next", EditorInfo.IME_ACTION_NEXT)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                maxLines = 1
                filters = arrayOf(InputFilter.LengthFilter(16))
                background = null
                setPadding(24.dp(), 0, 24.dp(), 0)
            }

            ivSearch = imageView {
                setImageResource(R.drawable.ic_search_fill)
            }

            tvNoResult = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorText))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                visibility = View.GONE
                text = getString(R.string.no_result_found)
                gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
            }

            btnSearch = normalButton {
                insetTop = 0
                insetBottom = 0
                setTextColor(ThemeHandler.getColor(Colors.colorBackground))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                backgroundTintList = ColorStateList.valueOf(ThemeHandler.getColor(Colors.colorText))
                text = getString(R.string.search)
                cornerRadius = 14.dp()
            }

            flSearch = frameLayout {

                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                    setCornerSize(14.dpf())
                }

                addView(ivSearch, customWithAndHeight(24, 24).apply {
                    gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                    leftMargin = 8.dp()
                })

                addView(etSearch, matchWidthCustomHeight(48) {
                    rightMargin = 8.dp()
                    leftMargin = 8.dp()
                })
            }

            llSearch = linearLayout {

                orientation = LinearLayout.HORIZONTAL

                addView(flSearch, customWidthAndHeight(0, 48) {
                    weight = 1f
                    rightMargin = 8.dp()
                })

                addView(btnSearch, customWidthAndHeight(100, 48))
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

            addView(tvToolbar, matchWidthWrapHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            })

            addView(llSearch, matchWidthCustomHeight(48) {
                gravity = Gravity.TOP
                topMargin = 50.dp()
                rightMargin = 32.dp()
                leftMargin = 32.dp()
            })

            addView(rvTopHeadLines, matchWidthAndHeight {
                gravity = Gravity.TOP
                topMargin = 102.dp()
                rightMargin = 8.dp()
                leftMargin = 8.dp()
            })

            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })

            addView(tvNoResult,matchWidthWrapHeight {
                gravity = Gravity.CENTER
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

        btnSearch.setOnClickListener {
            submitSearch()
        }
    }

    private fun submitSearch() {
        viewModel.searchTag = etSearch.text.toString()
        viewModel.searchNews()
        btnSearch.closeKeyboard()
    }

    override fun daggerConfiguration() {
        DaggerSearchNewsComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}