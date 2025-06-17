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


fun LinearLayout.customWidthAndHeight(
    wInDp: Int,
    hInDp: Int,
    initLayout: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        wInDp.dp(),
        hInDp.dp()
    ).apply(initLayout)
}

fun LinearLayout.customWidthAndWrapHeight(
    wInDp: Int,
    initLayout: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        wInDp.dp(),
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}


fun LinearLayout.matchWidthAndCustomHeight(
    hInDp: Int,
    initLayout: LinearLayout.LayoutParams.() -> Unit = {}
): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        hInDp.dp()
    ).apply(initLayout)
}

fun LinearLayout.matchWidthWrapHeight(initLayout: LinearLayout.LayoutParams.() -> Unit = {}): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}

fun LinearLayout.matchWidthHeight(initLayout: LinearLayout.LayoutParams.() -> Unit = {}): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
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

fun FrameLayout.matchWidthCustomHeight(
    hInDp: Int, initLayout: FrameLayout.LayoutParams.() -> Unit = {}
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        hInDp.dp()
    ).apply(initLayout)
}

fun FrameLayout.customWithAndHeight(
    hInDp: Int, wInDp: Int,
    initLayout: FrameLayout.LayoutParams.() -> Unit = {}
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        wInDp.dp(),
        hInDp.dp()
    ).apply(initLayout)
}

fun FrameLayout.wrapWidthAndHeight(initLayout: FrameLayout.LayoutParams.() -> Unit = {}): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}


