package com.gradlevv.search.data.source

import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.search.data.SearchNewsMapper
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val searchNewsService: SearchNewsService,
    private val mapper: SearchNewsMapper
) : ResponseHandler(), SearchNewsRepository {

    override suspend fun searchNews(tag: String): Resource<List<SearchNewsItem>> {
        return withContext(Dispatchers.IO) {
            return@withContext getResource { searchNewsService.searchNews(tag) }.mapTo(mapper)
        }
    }
}