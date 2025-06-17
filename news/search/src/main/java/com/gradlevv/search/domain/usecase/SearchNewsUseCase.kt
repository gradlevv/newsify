package com.gradlevv.search.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: SearchNewsRepository
) {
    suspend operator fun invoke(params: Params): Result<List<SearchNewsItem>> {
        return repository.searchNews(params = params)
    }

    data class Params(
        val tag: String,
        val from: String,
        val to: String,
        val sortedBy: String
    )
}