package com.gradlevv.ui.utils

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.gradlevv.core.util.dp


fun ViewGroup.matchWidthCustomHeight(hInDp: Int): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        hInDp.dp()
    )
}

fun LinearLayout.matchWidthWrapHeight(initLayout: LinearLayout.LayoutParams.() -> Unit = {}): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}

fun FrameLayout.matchWidthWrapHeight(initLayout: FrameLayout.LayoutParams.() -> Unit = {}): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}

fun FrameLayout.matchWidthAndHeight(initLayout: FrameLayout.LayoutParams.() -> Unit = {}): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).apply(initLayout)
}

fun FrameLayout.wrapWidthAndHeight(initLayout: FrameLayout.LayoutParams.() -> Unit = {}): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}


