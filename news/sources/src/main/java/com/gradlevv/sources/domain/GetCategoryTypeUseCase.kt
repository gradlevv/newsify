package com.gradlevv.sources.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: SourcesRepository) {

    operator fun invoke(): Flow<List<CategoryItem>> {
        return repository.getCategoryList()
    }
}