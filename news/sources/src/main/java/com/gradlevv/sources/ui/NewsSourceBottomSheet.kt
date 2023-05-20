package com.gradlevv.sources.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.gradlevv.core.util.dp
import com.gradlevv.core.util.dpf
import com.gradlevv.sources.R
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.ui.dsl.linearLayout
import com.gradlevv.ui.dsl.normalButton
import com.gradlevv.ui.dsl.textView
import com.gradlevv.ui.shape.materialShape
import com.gradlevv.ui.utils.Colors
import com.gradlevv.ui.utils.ThemeHandler
import com.gradlevv.ui.utils.matchWidthWrapHeight

class NewsSourceBottomSheet(private val context: Context) {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnGoToWebSite: MaterialButton

    private var mBottomSheetDialog: BottomSheetDialog? = null
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var mBaseView: LinearLayout? = null

    init {
        init()
    }

    private fun init() {
        mBaseView = createView()
        mBottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
    }

    private fun createView(): LinearLayout {
        return with(context) {
            return@with linearLayout {
                orientation = LinearLayout.VERTICAL

                background = materialShape {
                    fillColor = ThemeHandler.getColorState(Colors.colorBackground)
                    setCornerSize(8.dpf())
                }

                tvTitle = textView {
                    setTextColor(ThemeHandler.getColor(Colors.colorText))
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    gravity = Gravity.CENTER
                }

                tvDescription = textView {
                    setTextColor(ThemeHandler.getColor(Colors.colorText))
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                }

                btnGoToWebSite = normalButton {
                    text = context.getString(R.string.got_to_website)
                    insetTop = 0
                    insetBottom = 0
                    setTextColor(ThemeHandler.getColor(Colors.colorBackground))
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                    cornerRadius = 14.dp()
                    backgroundTintList =
                        ColorStateList.valueOf(ThemeHandler.getColor(Colors.colorText))
                }

                addView(tvTitle, matchWidthWrapHeight {
                    topMargin = 16.dp()
                    rightMargin = 16.dp()
                    leftMargin = 16.dp()
                })

                addView(tvDescription, matchWidthWrapHeight {
                    topMargin = 16.dp()
                    rightMargin = 16.dp()
                    leftMargin = 16.dp()
                })

                addView(btnGoToWebSite, matchWidthWrapHeight {
                    topMargin = 32.dp()
                    rightMargin = 16.dp()
                    leftMargin = 16.dp()
                    bottomMargin = 20.dp()
                })
            }
        }
    }

    private fun dismiss() {
        mBottomSheetDialog?.let {
            it.dismiss()
            mBottomSheetDialog = null
        }
    }

    fun show() {
        if (mBaseView == null) return

        mBottomSheetDialog?.setContentView(mBaseView!!)
        bottomSheetBehavior = BottomSheetBehavior.from(mBaseView!!.parent as View)
        bottomSheetBehavior?.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED

        mBottomSheetDialog?.show()
    }

    fun setValues(item: SourceItem): NewsSourceBottomSheet {
        tvTitle.text = item.name
        tvDescription.text = item.description

        return this
    }

    fun setOnSubmitClickListener(listener: () -> Unit): NewsSourceBottomSheet {
        btnGoToWebSite.setOnClickListener {
            listener.invoke()
            dismiss()
        }
        return this
    }
}