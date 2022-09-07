package com.gradlevv.ui.component

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.OnThemeChangeListener
import com.gradlevv.ui.utils.ThemeHandler

class CustomTextView(context: Context) : AppCompatTextView(context), OnThemeChangeListener {

    private var textColor: String = Colors.colorText
        set(value) {
            field = value
            setTextColor(ThemeHandler.getColor(value))
        }

    init {
        id = View.generateViewId()
    }

    override fun onThemeChange() {
        setTextColor(ThemeHandler.getColor(textColor))
    }
}