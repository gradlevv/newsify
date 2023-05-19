package com.gradlevv.sources.domain

import com.gradlevv.core.data.model.Result
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
    suspend fun getSourceList(): Result<List<SourceItemDomainModel>>
    fun getCategoryList(): Flow<List<CategoryItem>>
}