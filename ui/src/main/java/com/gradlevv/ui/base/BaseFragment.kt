package com.gradlevv.ui.base

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.util.NavigationModel
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import androidx.core.net.toUri
import com.gradlevv.newsify.ui.R

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerConfiguration()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initLayout()
    }

    abstract fun daggerConfiguration()
    abstract fun initLayout(): View?
    abstract fun setUpUi()

    protected open fun onThemeChanged() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

        viewModel.navigationCommand().observe(viewLifecycleOwner) {
            when(it){

                is NavigationModel.To -> {

                }

                is NavigationModel.ToDeppLink -> {
                    findNavController().navigate(
                        requireContext().getString(it.deepLink).toUri(),it.navOptions
                    )
                }

                is NavigationModel.Back -> {

                }
            }
        }

        viewModel.navigateUpEvent().observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                requireActivity().onBackPressed()
            }
        }

        viewModel.errorMessage().observe(viewLifecycleOwner){
            when(it){
                is ApiError.ServiceError -> {
                    showSnackbar(it.message)
                }

                is ApiError.RateLimitError -> {
                    showSnackbar(getString(R.string.rate_limit_error))
                }

                is ApiError.Unavailable -> {
                    showSnackbar(getString(R.string.service_unavailable))
                }
            }
        }
    }

    protected fun showSnackbar(text: String) {
        val snackbar = Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG)
        val textView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ThemeHandler.getColor(Colors.colorAccent))
        snackbar.setBackgroundTint(ThemeHandler.getColor(Colors.colorText))

        snackbar.show()
    }
}