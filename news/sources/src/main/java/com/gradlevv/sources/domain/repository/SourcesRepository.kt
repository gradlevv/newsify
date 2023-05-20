package com.gradlevv.sources.domain.repository

import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItemDomainModel
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
    suspend fun getSourceList(): Result<List<SourceItemDomainModel>>
    fun getCategoryList(): Flow<List<CategoryItem>>
}