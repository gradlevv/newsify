package com.gradlevv.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samcrow.core.base.BaseViewModel

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerSetUp()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createUi()
    }

    abstract fun daggerSetUp()
    abstract fun createUi(): View?
    abstract fun setUpUi()

    protected open fun onThemeChanged() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

        viewModel.navigationCommand().observe(viewLifecycleOwner, {

        })

        viewModel.navigateUpEvent().observe(viewLifecycleOwner, {
            if (it.getContentIfNotHandled() == true) {
                requireActivity().onBackPressed()
            }
        })
    }

}