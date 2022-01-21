package com.gradlevv.ui.shape

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.shape.MaterialShapeDrawable

inline fun View.materialShape(init: MaterialShapeDrawable.() -> Unit):MaterialShapeDrawable {
    return MaterialShapeDrawable().apply(init)
}

inline fun Fragment.materialShape(init: MaterialShapeDrawable.() -> Unit): MaterialShapeDrawable {
    return MaterialShapeDrawable().apply(init)
}