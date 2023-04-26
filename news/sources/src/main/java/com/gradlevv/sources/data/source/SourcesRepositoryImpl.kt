package com.gradlevv.sources.data.source

import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.core.util.IoDispatcher
import com.gradlevv.sources.data.SourcesMapper
import com.gradlevv.sources.data.model.CategoryType
import com.gradlevv.sources.domain.CategoryItem
import com.gradlevv.sources.domain.SourceItemDomainModel
import com.gradlevv.sources.domain.SourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val service: SourcesService,
    private val mapper: SourcesMapper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : ResponseHandler(), SourcesRepository {

    override suspend fun getSourceList(): Result<List<SourceItemDomainModel>> {
        return withContext(dispatcher) {
            return@withContext getResource { service.getSourceList() }.mapTo(mapper)
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
            )
        }
        return categoryList
    }
}