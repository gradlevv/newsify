package com.gradlevv.sources.domain.repository

import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.sources.domain.usecase.SourceTag

interface SourcesRepository {
    suspend fun getSourceList(type: SourceTag): Result<List<SourceItem>>
    fun getCategoryList(): List<CategoryItem>
}