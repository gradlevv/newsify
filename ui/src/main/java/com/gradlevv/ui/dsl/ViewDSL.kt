package com.gradlevv.ui.dsl

import android.view.ViewGroup
import android.widget.TextView

inline fun ViewGroup.textView(init : TextView.() -> Unit): TextView {
    return TextView(context).apply(init)
}