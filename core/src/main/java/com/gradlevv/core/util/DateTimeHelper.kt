package com.gradlevv.core.util

import com.gradlevv.core.util.Constants.DATE_FORMAT
import com.gradlevv.core.util.Constants.SERVER_DATE_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface DateTimeHelper {
    fun simpleDateFormat(serverDate: String): String?
}

class DateTimeHelperImpl @Inject constructor() : DateTimeHelper {

    override fun simpleDateFormat(serverDate: String): String? {
        val serverDateFormat: DateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.US)
        val date = serverDateFormat.parse(serverDate) ?: return null
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)

        return simpleDateFormat.format(date)
    }
}