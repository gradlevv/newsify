package com.gradlevv.ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.gradlevv.core.util.getCompatDrawable


fun ImageView.setCompatDrawable(@DrawableRes id: Int) {
    setImageDrawable(context.getCompatDrawable(id))
}

fun View.closeKeyboard() {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun View.openKeyboard(): Boolean {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
    return false
}