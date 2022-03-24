package com.gradlevv.list.domain.usecase

import com.gradlevv.core.data.model.Resource
import com.gradlevv.list.data.model.TopHeadLinesResponse
import com.gradlevv.list.domain.NewsListRepository
import javax.inject.Inject

class GetTopHeadLinesUseCase @Inject constructor(
    private val repository: NewsListRepository
) {
    suspend operator fun invoke(): Resource<TopHeadLinesResponse> {
        return repository.getTopHeadLines()
    }
}