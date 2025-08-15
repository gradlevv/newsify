package com.gradlevv.search.domain

import com.gradlevv.core.data.model.Result
import com.gradlevv.search.domain.usecase.SearchNewsUseCase.Params

interface SearchNewsRepository {
    suspend fun searchNews(params: Params): Result<List<SearchNewsItem>>
}