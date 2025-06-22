package com.gradlevv.sources.data.source

import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.map
import com.gradlevv.core.data.network.safeApiCall
import com.gradlevv.sources.domain.model.CategoryType
import com.gradlevv.sources.data.model.toDomain
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.sources.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val service: SourcesService,
) : SourcesRepository {

    override suspend fun getSourceList(type: String?): Result<List<SourceItem>> {
        return safeApiCall { service.getSourceList(type) }.map {
            val result = it.sourceList?.toDomain().orEmpty()
            return when {
                result.isEmpty() -> Result.Error(ApiError.NullError)
                else -> Result.Success(result)
            }
        }
    }

    override fun getCategoryList(): Flow<List<CategoryItem>> {
        return flow {
            emit(
                listOf(
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
            )
        }
    }
}