package com.gradlevv.search.data.source

import com.gradlevv.core.data.model.ApiError
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.data.model.map
import com.gradlevv.core.data.network.ResponseHandler
import com.gradlevv.core.data.network.safeApiCall
import com.gradlevv.search.data.model.toDomain
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val searchNewsService: SearchNewsService,
) : ResponseHandler(), SearchNewsRepository {

    override suspend fun searchNews(request: SearchDomainModel): Result<List<SearchNewsItem>> {
        return safeApiCall {
            searchNewsService.searchNews(
                tag = request.tag,
                from = request.from,
                to = request.to,
                sortedBy = request.sortedBy
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