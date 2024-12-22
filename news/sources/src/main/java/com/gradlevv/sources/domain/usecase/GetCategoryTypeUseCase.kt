package com.gradlevv.sources.domain.usecase

import com.gradlevv.sources.domain.repository.SourcesRepository
import com.gradlevv.sources.domain.model.CategoryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: SourcesRepository) {

    operator fun invoke(): Flow<List<CategoryItem>> {
        return repository.getCategoryList()
    }
}