package com.gradlevv.sources.domain

import com.gradlevv.core.data.model.Resource

interface SourcesRepository {
    suspend fun getSourceList(): Resource<List<SourceItemDomainModel>>
}