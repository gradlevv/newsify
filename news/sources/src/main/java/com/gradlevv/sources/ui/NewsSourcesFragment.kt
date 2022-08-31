package com.gradlevv.sources.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
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
import com.gradlevv.sources.domain.SourceItemDomainModel
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

class NewsSourcesFragment : BaseFragment<NewsSourcesViewModel>() {

    private lateinit var root: FrameLayout
    private lateinit var rvSourceList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var loading: ProgressBar
    private lateinit var tvToolbar: TextView

    private val newsSourcesAdapter: NewsSourcesAdapter by lazy {
        NewsSourcesAdapter(
            ::onItemClick
        )
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

        root = frameLayout {
            loading = ProgressBar(context).apply {
                isIndeterminate = true
                visibility = View.GONE
            }

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorWhite))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = getString(R.string.top_news_source_title)
                gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                }
                setPadding(0, 12.dp(), 0, 12.dp())
            }

            linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            rvSourceList = recyclerView {
                layoutManager = linearLayoutManager
                clipToPadding = false
                adapter = newsSourcesAdapter
                setPadding(0, 0, 0, 80.dp())
            }

            addView(tvToolbar, matchWidthWrapHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            })

            addView(loading, matchWidthWrapHeight {
                gravity = Gravity.CENTER
            })
            addView(rvSourceList, matchWidthAndHeight {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                topMargin = 42.dp()
                rightMargin = 8.dp()
                leftMargin = 8.dp()
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
                }

                if (topHeadLinesState.items.isNotEmpty()) {
                    loading.visibility = View.GONE
                    newsSourcesAdapter.submitList(topHeadLinesState.items)
                }

                if (topHeadLinesState.isError) {
                    loading.visibility = View.GONE
                }
            }
        }

    }

    override fun daggerConfiguration() {
        DaggerNewsSourcesComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}