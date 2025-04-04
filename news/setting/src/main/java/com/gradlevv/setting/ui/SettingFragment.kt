package com.gradlevv.setting.ui

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gradlevv.core.util.dp
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.utils.matchWidthWrapHeight

class SettingFragment : BaseFragment<SettingViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var tvTitle: TextView

    override val viewModel: SettingViewModel by viewModels()

    override fun initLayout(): View? {
        root = linearLayout {
            orientation = LinearLayout.VERTICAL

            tvTitle = textView {
                text = "Setting Fragment"
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

    override fun daggerConfiguration() {
    }
}