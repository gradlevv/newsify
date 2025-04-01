package com.gradlevv.core.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import javax.inject.Inject

interface IntentUtils {
    fun openLinkInDeviceBrowser(url: String?)
}

class IntentUtilsImpl @Inject constructor(
    private val context: Context
) : IntentUtils {

    override fun openLinkInDeviceBrowser(url: String?) {

        if (url?.isEmpty() == true) return

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            data = url?.toUri()
        }

        context.startActivity(intent)
    }
}