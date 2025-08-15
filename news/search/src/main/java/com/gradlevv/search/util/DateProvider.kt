package com.gradlevv.search.util

import org.threeten.bp.LocalDate
import javax.inject.Inject

interface DateProvider {
    fun today(): LocalDate
}

class DateProviderImpl @Inject constructor() : DateProvider {
    override fun today(): LocalDate = LocalDate.now()
}