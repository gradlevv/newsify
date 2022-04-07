package com.gradlevv.ui.dsl

import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.gradlevv.ui.R

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

inline fun ViewGroup.editText(init: EditText.() -> Unit): EditText {
    return EditText(context).apply(init)
}

inline fun ViewGroup.normalButton(init: MaterialButton.() -> Unit): MaterialButton {
    return MaterialButton(context,null,R.attr.borderlessButtonStyle)
}