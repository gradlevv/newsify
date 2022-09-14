package com.gradlevv.ui.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.gradlevv.core.util.dp

var ViewGroup.MarginLayoutParams.horizontalMargin: Int
    get() = 0
    set(value) {
        rightMargin = value
        leftMargin = value
    }

var ViewGroup.MarginLayoutParams.verticalMargin: Int
    get() = 0
    set(value) {
        topMargin = value
        bottomMargin = value
    }

fun ConstraintLayout.customWidthAndHeight(wInDp: Int, hInDp: Int): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(wInDp.dp(), hInDp.dp())
}

fun ConstraintLayout.wrapWidthCustomHeight(hInDp: Int): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        hInDp.dp()
    )
}

fun ConstraintLayout.matchWidthWrapHeight(): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
}

fun ConstraintLayout.wrapWidthWrapHeight(): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
}

fun ConstraintLayout.matchConstraintWrapHeight(): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
}

fun ConstraintLayout.matchConstraintWidthAndHeight(): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
    )
}

fun ViewGroup.customWidthAndHeight(wInDp: Int, hInDp: Int): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(wInDp.dp(), hInDp.dp())
}

fun ViewGroup.wrapWidthAndHeight(): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun ViewGroup.matchWidthAndWrapHeight(): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun ViewGroup.matchWidthCustomHeight(hInDp: Int): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        hInDp.dp()
    )
}

fun ViewGroup.matchWidthAndHeight(): ViewGroup.LayoutParams {
    return ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
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

fun LinearLayout.wrapWidthAndHeight(initLayout: LinearLayout.LayoutParams.() -> Unit = {}): LinearLayout.LayoutParams {
    return LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
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
    hInDp:Int, initLayout: FrameLayout.LayoutParams.() -> Unit = {}
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        hInDp.dp()
    ).apply(initLayout)
}

fun FrameLayout.customWithAndHeight(
    hInDp:Int, wInDp:Int
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        wInDp.dp(),
        hInDp.dp()
    )
}

fun FrameLayout.wrapWidthAndHeight(initLayout: FrameLayout.LayoutParams.() -> Unit = {}): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply(initLayout)
}

fun Context.frameLayout(initLayout: FrameLayout.() -> Unit = {}): FrameLayout {
    return FrameLayout(this).apply(initLayout)
}


