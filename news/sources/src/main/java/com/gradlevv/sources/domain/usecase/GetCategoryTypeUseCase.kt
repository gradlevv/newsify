package com.gradlevv.sources.domain.usecase

import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.repository.SourcesRepository
import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: SourcesRepository) {

    operator fun invoke(): List<CategoryItem> {
        return repository.getCategoryList()
    }
}