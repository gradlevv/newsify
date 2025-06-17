package com.gradlevv.ui.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.navigation.NavOptions
import com.bumptech.glide.Glide
import com.gradlevv.core.util.getCompatDrawable
import com.gradlevv.newsify.ui.R


fun ImageView.setCompatDrawable(@DrawableRes id: Int) {
    setImageDrawable(context.getCompatDrawable(id))
}

fun ImageView.loadImage(url: String?){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_place_holder)
        .into(this)
}

val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.enter_anim)
    .setExitAnim(R.anim.exit_anim)
    .setPopEnterAnim(R.anim.pop_enter_anim)
    .setPopExitAnim(R.anim.pop_exit_anim).build()