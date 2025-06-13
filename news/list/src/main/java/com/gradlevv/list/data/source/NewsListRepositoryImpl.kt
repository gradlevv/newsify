package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.map
import com.gradlevv.core.data.network.safeApiCall
import com.gradlevv.list.data.model.toDomain
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.model.CategoryType
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val service: NewsListService,
) : NewsListRepository {

    override suspend fun getTopHeadLines(category: String): Result<List<TopHeadLinesItem>> {
        return safeApiCall { service.getTopHeadLines(category) }.map {

            val result = it.articleList?.toDomain().orEmpty()
            return when {
                result.isEmpty() -> Result.Error(ApiError.NullError)
                else -> Result.Success(result)
            }
        }
    }

    override fun getCategoryList(): List<CategoryType> {
        val categoryList = listOf(
            CategoryType.General,
            CategoryType.Business,
            CategoryType.Entertainment,
            CategoryType.Sports,
            CategoryType.Technology,
            CategoryType.Science,
            CategoryType.Health
        )
        return categoryList
    }
}