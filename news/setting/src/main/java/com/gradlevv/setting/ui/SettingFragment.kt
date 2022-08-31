package com.gradlevv.setting.ui

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.gradlevv.core.di.ViewModelFactory
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dp
import com.gradlevv.setting.R
import com.gradlevv.setting.di.DaggerSettingComponent
import com.gradlevv.ui.base.BaseFragment
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.radioButton
import com.gradlevv.ui.dsl.radioGroup
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthAndCustomHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import javax.inject.Inject

class SettingFragment : BaseFragment<SettingViewModel>() {

    private lateinit var root: LinearLayout
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbLightTheme: RadioButton
    private lateinit var rbDarkTheme: RadioButton
    private lateinit var tvToolbar: TextView
    private lateinit var lineView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: SettingViewModel by viewModels { viewModelFactory }

    override fun initLayout(): View? {
        root = linearLayout {
            orientation = LinearLayout.VERTICAL

            tvToolbar = textView {
                setTextColor(ThemeHandler.getColor(Colors.colorWhite))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                text = getString(R.string.setting_fragment_title)
                gravity = Gravity.CENTER or Gravity.CENTER_HORIZONTAL
                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorText)
                }
                setPadding(0, 12.dp(), 0, 12.dp())
            }

            rbLightTheme = radioButton {
                text = getString(R.string.setting_light_theme)
                setTextColor(ThemeHandler.getColor(Colors.colorText))
            }

            rbDarkTheme = radioButton {
                text = getString(R.string.setting_dark_theme_title)
                setTextColor(ThemeHandler.getColor(Colors.colorText))
            }

            lineView = View(context).apply {
                setBackgroundColor(ThemeHandler.getColor(Colors.colorPrimary))
            }

            radioGroup = radioGroup {

                orientation = RadioGroup.VERTICAL

                addView(rbLightTheme, matchWidthWrapHeight())
                addView(lineView, matchWidthAndCustomHeight(1.dp()) {
                    topMargin = 8.dp()
                    leftMargin = 16.dp()
                    rightMargin = 16.dp()
                })
                addView(rbDarkTheme, matchWidthWrapHeight {
                    topMargin = 8.dp()
                })
            }

            addView(tvToolbar, matchWidthWrapHeight())

            addView(radioGroup, matchWidthWrapHeight {
                topMargin = 20.dp()
                rightMargin = 16.dp()
                leftMargin = 16.dp()
            })
        }
        return root
    }

    override fun setUpUi() {

    }

    override fun daggerConfiguration() {
        DaggerSettingComponent.factory().create(requireActivity().coreComponent()).inject(this)
    }
}