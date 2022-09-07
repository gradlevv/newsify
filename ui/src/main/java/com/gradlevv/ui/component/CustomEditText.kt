package com.gradlevv.ui.component

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.OnThemeChangeListener
import com.gradlevv.ui.utils.ThemeHandler

class CustomEditText(context: Context) : AppCompatEditText(context), OnThemeChangeListener {

    init {
        id = View.generateViewId()
    }

    override fun onThemeChange() {
        setTextColor(ThemeHandler.getColor(Colors.colorText))
        background = materialShape {
            fillColor = ThemeHandler.getColorState(Colors.colorText)
        }
    }
}