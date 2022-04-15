package com.gradlevv.ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.navigation.NavOptions
import com.bumptech.glide.Glide
import com.gradlevv.core.util.getCompatDrawable
import com.gradlevv.ui.R


fun ImageView.setCompatDrawable(@DrawableRes id: Int) {
    setImageDrawable(context.getCompatDrawable(id))
}

fun ImageView.loadImage(url: String?){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_place_holder)
        .into(this)
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

val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.enter_anim)
    .setExitAnim(R.anim.exit_anim)
    .setPopEnterAnim(R.anim.pop_enter_anim)
    .setPopExitAnim(R.anim.pop_exit_anim).build()