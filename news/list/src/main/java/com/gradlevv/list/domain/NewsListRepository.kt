package com.gradlevv.list.domain

import com.gradlevv.core.data.model.Result

interface NewsListRepository {

    suspend fun getTopHeadLines(category: String): Result<List<TopHeadLinesItem>>

    fun getCategoryList(): List<CategoryItem>
}