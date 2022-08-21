package com.gradlevv.search.domain

import com.gradlevv.core.data.model.Result

interface SearchNewsRepository {

    suspend fun searchNews(request: SearchDomainModel): Result<List<SearchNewsItem>>
}