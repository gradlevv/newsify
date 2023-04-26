package com.gradlevv.sources.ui

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.sources.R
import com.gradlevv.sources.di.DaggerNewsSourcesComponent
import com.gradlevv.sources.domain.CategoryItem
import com.gradlevv.sources.domain.SourceItemDomainModel
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.*
import com.gradlevv.ui.utils.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesFragment : BaseFragment<NewsSourcesViewModel>() {

    private lateinit var root: LinearLayout
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
//        viewModel.categoryChangeClick(getString(categoryItem.type))
    }

    private fun onItemClick(position: Int, item: SourceItemDomainModel) {

        NewsSourceBottomSheet(requireContext())
            .setOnSubmitClickListener {
                intentUtils.openLinkInDeviceBrowser(item.url)
            }.setValues(item).show()

    }

    @Inject
    lateinit var intentUtils: IntentUtils

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsSourcesViewModel by viewModels { viewModelFactory }

    override fun initLayout(): View? {

        root = linearLayout {

            orientation = LinearLayout.VERTICAL

            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            ivLogo = imageView {
                setCompatDrawable(R.drawable.ic_newsify)
            }

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorOnBackground100))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                typeface = Typeface.DEFAULT_BOLD
                text = getString(R.string.top_news_source_title)
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


            addView(ivLogo, matchWidthWrapHeight {
                topMargin = 32.dp()
            })

            addView(ScrollView(context).apply {

                addView(linearLayout {

                    orientation = LinearLayout.VERTICAL

                    addView(tvToolbar, matchWidthWrapHeight {
                        topMargin = 24.dp()
                        rightMargin = 16.dp()
                        leftMargin = 16.dp()
                    })

                    addView(rvCategories, matchWidthWrapHeight {
                        topMargin = 8.dp()
                        leftMargin = 8.dp()
                    })

                    addView(loading, matchWidthWrapHeight {

                    })
                    addView(rvSourceList, matchWidthAndHeight {
                        topMargin = 16.dp()
                        rightMargin = 8.dp()
                        leftMargin = 8.dp()
                    })

                }, matchWidthHeight())

            })
        }

        return root
    }

    override fun setUpUi() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.sourceList.flowWithLifecycle(
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

                loading.visibility = View.GONE
                newsSourcesAdapter.submitList(topHeadLinesState.items)
                return@collect

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

    override fun daggerConfiguration() {
        DaggerNewsSourcesComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}