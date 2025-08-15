package com.gradlevv.list.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.model.CategoryType

fun interface GetCategoryTypeUseCase : () -> List<CategoryType>

fun interface GetTopHeadLinesUseCase : suspend (CategoryTag) -> Result<List<TopHeadLinesItem>>

@JvmInline
value class CategoryTag(val value: String)