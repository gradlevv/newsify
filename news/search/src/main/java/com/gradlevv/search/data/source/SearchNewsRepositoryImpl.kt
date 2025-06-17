package com.gradlevv.search.data.source

import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.map
import com.gradlevv.core.data.network.safeApiCall
import com.gradlevv.search.data.model.toDomain
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import com.gradlevv.search.domain.usecase.SearchNewsUseCase.Params
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val searchNewsService: SearchNewsService,
) : SearchNewsRepository {

    override suspend fun searchNews(params: Params): Result<List<SearchNewsItem>> {
        return safeApiCall {
            searchNewsService.searchNews(
                tag = params.tag,
                from = params.from,
                to = params.to,
                sortedBy = params.sortedBy
            )
        }.map {
            val result = it.articleList?.toDomain().orEmpty()
            return when {
                result.isEmpty() -> Result.Error(ApiError.NullError)
                else -> Result.Success(result)
            }
        }
    }
}