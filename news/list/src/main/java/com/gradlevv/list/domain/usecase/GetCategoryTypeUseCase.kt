package com.gradlevv.list.domain.usecase

import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.model.CategoryType
import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: NewsListRepository) {

    operator fun invoke(): List<CategoryType> {
        return repository.getCategoryList()
    }
}