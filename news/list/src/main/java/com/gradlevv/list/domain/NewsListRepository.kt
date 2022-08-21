package com.gradlevv.list.domain

import com.gradlevv.core.data.model.Result

interface NewsListRepository {

    suspend fun getTopHeadLines(): Result<List<TopHeadLinesItem>>
}