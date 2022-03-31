package com.gradlevv.search.data.source

import com.gradlevv.search.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchNewsService {

    @GET("v2/everything?")
    suspend fun searchNews(@Query("q=") tag : String) : Response<SearchResponse>
}