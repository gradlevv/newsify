package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.list.data.TopHeadLinesMapper
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.TopHeadLinesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val service: NewsListService,
    private val topHeadLinesMapper: TopHeadLinesMapper
) : ResponseHandler(), NewsListRepository {

    override suspend fun getTopHeadLines(): Resource<List<TopHeadLinesItem>> {
        return withContext(Dispatchers.IO) {
            return@withContext getResource { service.getTopHeadLines() }
                .mapTo(mapper = topHeadLinesMapper)
        }
    }
}