package com.gradlevv.search.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: SearchNewsRepository
) {
    suspend operator fun invoke(request: SearchDomainModel): Result<List<SearchNewsItem>> {
        return repository.searchNews(request = request)
    }
}