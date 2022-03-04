package com.gradlevv.list.ui

import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.list.di.DaggerNewsListComponent
import com.gradlevv.ui.base.BaseFragment
import javax.inject.Inject

class NewsListFragment : BaseFragment<NewsListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewsListViewModel by viewModels { viewModelFactory }

    override fun createUi(): View? {
        return TextView(context)
    }

    override fun setUpUi() {

    }

    override fun daggerSetUp() {
        DaggerNewsListComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}