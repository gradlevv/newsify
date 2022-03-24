package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.list.data.model.TopHeadLinesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListRepositoryImpl @Inject constructor(
    private val service: NewsListService
    ) : ResponseHandler(),NewsListRepository {

    override suspend fun getTopHeadLines(): Resource<TopHeadLinesResponse> {
        return withContext(Dispatchers.IO){
            return@withContext getResource { service.getTopHeadLines() }
        }
    }
}