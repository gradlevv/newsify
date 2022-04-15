package com.gradlevv.list.ui

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.navGraphViewModels
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.list.R
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.linearLayout
import javax.inject.Inject

class NewsDetailFragment : BaseFragment<NewsListViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var tvContent: TextView
    private lateinit var tvNewsDate: TextView
    private lateinit var ivNewsImage: ImageView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsListViewModel by navGraphViewModels(R.id.news_list_graph) { viewModelFactory }

    override fun createUi(): View? {
        root = linearLayout {

        }
        return root
    }

    override fun setUpUi() {

    }

    override fun daggerSetUp() {
        DaggerNewsListComponent.factory()
            .create(requireActivity().coreComponent()).inject(this)
    }
}