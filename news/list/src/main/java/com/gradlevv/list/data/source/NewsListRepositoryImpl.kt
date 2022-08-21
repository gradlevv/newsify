package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.mapTo
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.core.util.IoDispatcher
import com.gradlevv.list.data.TopHeadLinesMapper
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.TopHeadLinesItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val service: NewsListService,
    private val topHeadLinesMapper: TopHeadLinesMapper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : ResponseHandler(), NewsListRepository {

    override suspend fun getTopHeadLines(): Result<List<TopHeadLinesItem>> {
        return withContext(dispatcher) {
            return@withContext getResource { service.getTopHeadLines() }
                .mapTo(mapper = topHeadLinesMapper)
        }
    }
}