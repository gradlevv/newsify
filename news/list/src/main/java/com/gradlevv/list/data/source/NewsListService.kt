package com.gradlevv.list.data.source

import com.gradlevv.list.data.model.TopHeadLinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListService {

    @GET("v2/top-headlines?country=us")
    suspend fun getTopHeadLines(@Query("category") category: String): Response<TopHeadLinesResponse>
}