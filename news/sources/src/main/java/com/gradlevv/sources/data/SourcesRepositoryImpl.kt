package com.gradlevv.sources.data

import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.sources.data.source.SourcesService
import com.gradlevv.sources.domain.SourceItemDomainModel
import com.gradlevv.sources.domain.SourcesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val service: SourcesService,
    private val mapper: SourcesMapper
) : ResponseHandler(), SourcesRepository {

    override suspend fun getSourceList(): Resource<List<SourceItemDomainModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext getResource { service.getSourceList() }.mapTo(mapper)
        }
    }
}