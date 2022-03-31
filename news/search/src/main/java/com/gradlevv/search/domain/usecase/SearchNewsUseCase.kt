package com.gradlevv.search.domain.usecase

import com.gradlevv.core.data.model.Resource
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: SearchNewsRepository
) {
    suspend operator fun invoke(tag:String): Resource<List<SearchNewsItem>> {
        return repository.searchNews(tag)
    }
}