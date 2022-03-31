package com.gradlevv.search.domain

import com.gradlevv.core.data.model.Resource

interface SearchNewsRepository {

    suspend fun searchNews(tag: String): Resource<List<SearchNewsItem>>
}