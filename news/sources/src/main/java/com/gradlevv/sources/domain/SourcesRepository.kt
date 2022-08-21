package com.gradlevv.sources.domain

import com.gradlevv.core.data.model.Result

interface SourcesRepository {
    suspend fun getSourceList(): Result<List<SourceItemDomainModel>>
}