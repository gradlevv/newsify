package com.gradlevv.sources.data.source

import com.gradlevv.sources.data.model.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SourcesService {
    @GET("v2/top-headlines/sources?")
    suspend fun getSourceList(@Query ("type") type:String?): Response<SourcesResponse>
}