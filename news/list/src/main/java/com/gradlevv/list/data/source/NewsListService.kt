package com.gradlevv.list.data.source

import com.gradlevv.list.data.model.TopHeadLinesResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsListService {

    @GET("v2/top-headlines?country=us")
    suspend fun getTopHeadLines() : Response<TopHeadLinesResponse>
}