package com.gradlevv.ui.dsl

import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


inline fun Fragment.linearLayout(init : LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(requireContext()).apply(init)
}

inline fun Fragment.frameLayout(init : FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(requireContext()).apply(init)
}