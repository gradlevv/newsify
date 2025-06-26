package com.gradlevv.sources.ui.state

import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem

data class NewsSourceState(
    val isLoading: Boolean = false,
    val items: List<SourceItem> = emptyList(),
    val selectedCategory: CategoryItem? = null,
    val isError: Boolean = false
) {
    companion object {
        val Empty = NewsSourceState()
    }
}