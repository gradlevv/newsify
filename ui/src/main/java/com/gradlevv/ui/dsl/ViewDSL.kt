package com.gradlevv.ui.dsl

import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.R
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

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