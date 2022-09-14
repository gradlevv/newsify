package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.core.util.IoDispatcher
import com.gradlevv.list.data.TopHeadLinesMapper
import com.gradlevv.list.data.model.CategoryType
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.TopHeadLinesItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val service: NewsListService,
    private val topHeadLinesMapper: TopHeadLinesMapper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : ResponseHandler(), NewsListRepository {

    override suspend fun getTopHeadLines(category: String): Result<List<TopHeadLinesItem>> {
        return withContext(dispatcher) {
            return@withContext getResource { service.getTopHeadLines(category) }
                .mapTo(mapper = topHeadLinesMapper)
        }
    }

    override fun getCategoryList(): List<CategoryItem> {
        val categoryList = listOf(
            CategoryType.General,
            CategoryType.Business,
            CategoryType.Entertainment,
            CategoryType.Sports,
            CategoryType.Technology,
            CategoryType.Science,
            CategoryType.Health
        ).map { categoryType ->
            CategoryItem(
                type = categoryType.type,
                categoryName = categoryType.categoryName,
                icon = categoryType.icon
            )
        }
        return categoryList
    }
}