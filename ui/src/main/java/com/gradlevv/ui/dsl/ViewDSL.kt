package com.gradlevv.ui.dsl

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

inline fun ViewGroup.textView(init : TextView.() -> Unit): TextView {
    return TextView(context).apply(init)
}

inline fun ViewGroup.imageView(init: ImageView.() -> Unit): ImageView {
    return ImageView(context).apply(init)
}

inline fun ViewGroup.linearLayout(init : LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(context).apply(init)
}

inline fun ViewGroup.frameLayout(init : FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(context).apply(init)
}

inline fun ViewGroup.recyclerView(init: RecyclerView.() -> Unit): RecyclerView {
    return RecyclerView(context).apply(init)
}