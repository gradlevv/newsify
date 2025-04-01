package com.gradlevv.search.ui

import android.text.InputFilter
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.newsify.ui.R
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.editText
import com.gradlevv.ui.dsl.frameLayout
import com.gradlevv.ui.dsl.imageView
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.recyclerView
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.shape.rippleDrawable
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.customWidthAndHeight
import com.gradlevv.ui.utils.customWidthAndWrapHeight
import com.gradlevv.ui.utils.matchWidthAndCustomHeight
import com.gradlevv.ui.utils.matchWidthCustomHeight
import com.gradlevv.ui.utils.matchWidthHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import com.gradlevv.ui.utils.setCompatDrawable
import com.gradlevv.ui.utils.wrapWidthAndHeight
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : BaseFragment<SearchNewsViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var rvSearchNews: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var etSearch: EditText
    private lateinit var flSearch: FrameLayout
    private lateinit var ivSearch: ImageView
    private lateinit var btnSearch: TextView
    private lateinit var llSearch: LinearLayout
    private lateinit var tvNoResult: TextView
    private lateinit var ivLogo: ImageView

    private val searchNewsAdapter: SearchNewsAdapter by lazy {
        SearchNewsAdapter(
            ::onItemClick
        )
    }

    private fun onItemClick(position: Int, item: SearchNewsItem) {
        viewModel.navigateToDetailFragment(item)
    }

    override val viewModel: SearchNewsViewModel by hiltNavGraphViewModels(R.id.main_navigation)

    override fun initLayout(): View? {

        root = linearLayout {

            orientation = LinearLayout.VERTICAL

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            etSearch = editText {
                gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                setImeActionLabel("Done", EditorInfo.IME_ACTION_DONE)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                maxLines = 1
                filters = arrayOf(InputFilter.LengthFilter(16))
                background = null
                setHintTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                hint = getString(com.gradlevv.newsify.news.search.R.string.search_hint)
            }

            ivSearch = imageView {
                setImageResource(R.drawable.ic_right_secondary)
            }

            tvNoResult = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorText))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                visibility = View.GONE
                text = getString(com.gradlevv.newsify.news.search.R.string.no_result_found)
                gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
                visibility = View.GONE
            }

            btnSearch = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground70))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                gravity = Gravity.CENTER
                text = getString(com.gradlevv.newsify.news.search.R.string.cancel)
                background = rippleDrawable {
                    rippleColor = ThemeHandler.getColorWithAlpha(Colors.colorOnBackground50, 20)
                    drawable = materialShape { setCornerSize(16.dpf()) }
                }
            }

            flSearch = frameLayout {

                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorControllerVariant)
                    setCornerSize(14.dpf())
                }

                addView(ivSearch, wrapWidthAndHeight().apply {
                    gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
                    leftMargin = 8.dp()
                })

                addView(etSearch, matchWidthCustomHeight(40) {
                    rightMargin = 36.dp()
                    leftMargin = 40.dp()
                })
            }

            llSearch = linearLayout {

                orientation = LinearLayout.HORIZONTAL

                addView(flSearch, customWidthAndWrapHeight(0) {
                    weight = 1f
                    rightMargin = 8.dp()
                })

                addView(btnSearch, customWidthAndHeight(70, 40))
            }

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

            rvSearchNews = recyclerView {
                layoutManager = gridLayoutManager
                clipToPadding = false
                adapter = searchNewsAdapter
                setPadding(0, 0, 0, 80.dp())
            }

            addView(ivLogo, matchWidthWrapHeight {
                topMargin = 32.dp()
            })

            addView(llSearch, matchWidthAndCustomHeight(40) {
                topMargin = 32.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })

            addView(frameLayout {

                addView(tvNoResult, matchWidthWrapHeight {
                    topMargin = 64.dp()
                })

                addView(rvSearchNews, matchWidthHeight {
                    rightMargin = 8.dp()
                    leftMargin = 8.dp()
                })

                addView(loading, matchWidthWrapHeight {
                    topMargin = 64.dp()
                })
            }, matchWidthWrapHeight {
                topMargin = 8.dp()
            })

        }
        return root
    }

    override fun setUpUi() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.searchNewsList.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { searchList ->

                tvNoResult.visibility = View.GONE
                if (searchList.isLoading) {
                    loading.visibility = View.VISIBLE
                    return@collect
                }

                if (searchList.isError) {
                    loading.visibility = View.GONE
                    return@collect
                }

                searchNewsAdapter.submitList(searchList.items)

                if (searchList.items.isEmpty()) {
                    loading.visibility = View.GONE
                    tvNoResult.visibility = View.VISIBLE
                    return@collect
                }

                tvNoResult.visibility = View.GONE
                loading.visibility = View.GONE

            }
        }

        etSearch.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                viewModel.setSearchValue(it.toString())
            }
        }

        btnSearch.setOnClickListener {
            etSearch.text.clear()
        }
    }

    override fun daggerConfiguration() {
    }
}