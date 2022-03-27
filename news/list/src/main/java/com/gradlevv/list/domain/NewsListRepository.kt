package com.gradlevv.list.domain

import com.gradlevv.core.data.model.Resource

interface NewsListRepository {

    suspend fun getTopHeadLines(): Resource<List<TopHeadLinesItem>>
}