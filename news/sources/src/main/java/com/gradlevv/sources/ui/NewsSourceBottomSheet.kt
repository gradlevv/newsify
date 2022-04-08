package com.gradlevv.sources.ui

import android.content.Context
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class NewsSourceBottomSheet ( private val context: Context) {

    private var mBottomSheetDialog: BottomSheetDialog? = null
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var mBaseView: LinearLayout? = null

    init {
        init()
    }

    private fun init(){
        mBaseView = createView()
        mBottomSheetDialog = BottomSheetDialog(context,)
    }

    private fun createView(): LinearLayout? {
        TODO("Not yet implemented")
    }

    fun dismiss(){

    }

    fun show(){

    }
}