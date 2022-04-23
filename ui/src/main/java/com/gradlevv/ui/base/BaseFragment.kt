package com.gradlevv.ui.base

import android.graphics.Color
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
import com.gradlevv.core.util.NavigationCommand
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeManager

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

        viewModel.navigationCommand().observe(viewLifecycleOwner) {
            when(it){

                is NavigationCommand.To -> {

                }

                is NavigationCommand.ToDeppLink -> {
                    findNavController().navigate(
                        Uri.parse(requireContext().getString(it.deepLink)),it.navOptions
                    )
                }

                is NavigationCommand.Back -> {

                }
            }
        }

        viewModel.navigateUpEvent().observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() == true) {
                requireActivity().onBackPressed()
            }
        }

        viewModel.errorMessage().observe(viewLifecycleOwner){
            it?.let {
                showSnackbar(it)
            }
        }
    }

    protected fun showSnackbar(text: String) {
        val snackbar = Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG)
        val textView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ThemeManager.getColor(Colors.colorAccent))
        snackbar.setBackgroundTint(ThemeManager.getColor(Colors.colorText))

        snackbar.show()
    }
}