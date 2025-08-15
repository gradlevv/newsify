package com.gradlevv.sources.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem

fun interface GetCategoryTypeUseCase : () -> List<CategoryItem>

fun interface GetSourceListUseCase : suspend (SourceTag) -> Result<List<SourceItem>>

@JvmInline
value class SourceTag(val value: String)