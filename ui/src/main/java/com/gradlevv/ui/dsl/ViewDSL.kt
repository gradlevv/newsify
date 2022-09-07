package com.gradlevv.ui.dsl

import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.gradlevv.ui.R
import com.gradlevv.ui.component.CustomEditText
import com.gradlevv.ui.component.CustomTextView

inline fun ViewGroup.textView(init: CustomTextView.() -> Unit): CustomTextView {
    return CustomTextView(context).apply(init)
}

inline fun ViewGroup.imageView(init: ImageView.() -> Unit): ImageView {
    return ImageView(context).apply(init)
}

inline fun ViewGroup.linearLayout(init: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(context).apply(init)
}

inline fun ViewGroup.frameLayout(init: FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(context).apply(init)
}

inline fun ViewGroup.recyclerView(init: RecyclerView.() -> Unit): RecyclerView {
    return RecyclerView(context).apply(init)
}

inline fun ViewGroup.editText(init: CustomEditText.() -> Unit): CustomEditText {
    return CustomEditText(context).apply(init)
}

inline fun ViewGroup.normalButton(init: MaterialButton.() -> Unit): MaterialButton {
    return MaterialButton(context, null, R.attr.borderlessButtonStyle).apply(init)
}

inline fun ViewGroup.radioGroup(init: RadioGroup.() -> Unit): RadioGroup {
    return RadioGroup(context).apply(init)
}

inline fun ViewGroup.radioButton(init: RadioButton.() -> Unit): RadioButton {
    return RadioButton(context).apply(init)
}