package com.gradlevv.list.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.TopHeadLinesItem
import javax.inject.Inject

class GetTopHeadLinesUseCase @Inject constructor(
    private val repository: NewsListRepository
) {
    suspend operator fun invoke(category: String): Result<List<TopHeadLinesItem>> {
        return repository.getTopHeadLines(category)
    }
}