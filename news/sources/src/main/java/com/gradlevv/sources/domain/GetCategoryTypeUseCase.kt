package com.gradlevv.sources.domain

import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: SourcesRepository) {

    operator fun invoke(): List<CategoryItem> {
        return repository.getCategoryList()
    }
}