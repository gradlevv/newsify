package com.gradlevv.list.domain

import com.gradlevv.core.data.model.Result
import com.gradlevv.list.domain.model.CategoryType

interface NewsListRepository {

    suspend fun getTopHeadLines(category: String): Result<List<TopHeadLinesItem>>

    fun getCategoryList(): List<CategoryType>
}