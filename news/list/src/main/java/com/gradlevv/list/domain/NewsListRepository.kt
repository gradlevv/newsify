package com.gradlevv.list.domain

import com.gradlevv.core.data.model.Result
import com.gradlevv.list.domain.model.CategoryType
import com.gradlevv.list.domain.usecase.CategoryTag

interface NewsListRepository {

    suspend fun getTopHeadLines(category: CategoryTag): Result<List<TopHeadLinesItem>>

    fun getCategoryList(): List<CategoryType>
}