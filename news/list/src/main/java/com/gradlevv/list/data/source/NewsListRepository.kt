package com.gradlevv.list.data.source

import com.gradlevv.core.data.model.Resource
import com.gradlevv.list.data.model.TopHeadLinesResponse

interface NewsListRepository {

    suspend fun getTopHeadLines() : Resource<TopHeadLinesResponse>
}