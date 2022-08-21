package com.gradlevv.search.data.source

import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.core.util.IoDispatcher
import com.gradlevv.search.data.SearchNewsMapper
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val searchNewsService: SearchNewsService,
    private val mapper: SearchNewsMapper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : ResponseHandler(), SearchNewsRepository {

    override suspend fun searchNews(request: SearchDomainModel): Result<List<SearchNewsItem>> {
        return withContext(dispatcher) {
            return@withContext getResource {
                searchNewsService.searchNews(
                    tag = request.tag,
                    from = request.from,
                    to = request.to,
                    sortedBy = request.sortedBy
                )
            }.mapTo(mapper)
        }
    }
}