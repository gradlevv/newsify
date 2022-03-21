package com.gradlevv.favorite.ui

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.favorite.di.DaggerFavoriteNewsComponent
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.utils.matchWidthWrapHeight
import javax.inject.Inject

class FavoriteNewsFragment: BaseFragment<FavoriteNewsViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var tvTitle: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: FavoriteNewsViewModel by viewModels { viewModelFactory }

    override fun createUi(): View? {
        root = linearLayout {
            orientation = LinearLayout.VERTICAL

            tvTitle = textView {
                text = "Favorite News Fragment"
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
            }

            addView(tvTitle, matchWidthWrapHeight {
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })
        }
        return root
    }

    override fun setUpUi() {

    }

    override fun daggerSetUp() {
        DaggerFavoriteNewsComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}