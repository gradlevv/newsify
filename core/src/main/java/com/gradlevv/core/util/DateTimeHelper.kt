package com.gradlevv.core.util

import com.gradlevv.core.util.Constants.DATE_FORMAT_SHORT
import com.gradlevv.core.util.Constants.SERVER_DATE_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    fun simpleDateFormat(serverDate: String): String? {
        val serverDateFormat: DateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.US)
        val date = serverDateFormat.parse(serverDate) ?: return null
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_SHORT, Locale.US)

        return simpleDateFormat.format(date)
    }
}