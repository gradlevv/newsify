package com.gradlevv.sources.data.source

import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.sources.data.SourcesMapper
import com.gradlevv.sources.data.model.CategoryType
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.sources.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val service: SourcesService,
    private val mapper: SourcesMapper
) : ResponseHandler(), SourcesRepository {

    override suspend fun getSourceList(type: String?): Result<List<SourceItem>> {
        return getResource { service.getSourceList(type) }.mapTo(mapper)
    }

    override fun getCategoryList(): Flow<List<CategoryItem>> {
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

        return flow { emit(categoryList) }
    }
}