package com.gradlevv.list.domain.usecase

import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.NewsListRepository
import javax.inject.Inject

class GetCategoryTypeUseCase @Inject constructor(private val repository: NewsListRepository) {

    operator fun invoke(): List<CategoryItem> {
        return repository.getCategoryList()
    }
}