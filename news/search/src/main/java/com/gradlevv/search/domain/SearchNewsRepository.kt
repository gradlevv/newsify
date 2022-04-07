package com.gradlevv.search.domain

import com.gradlevv.core.data.model.Resource

interface SearchNewsRepository {

    suspend fun searchNews(request: SearchDomainModel): Resource<List<SearchNewsItem>>
}