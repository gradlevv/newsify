package com.gradlevv.list.ui.state

import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.model.CategoryType

data class TopHeadLinesState(
    val isLoading: Boolean = false,
    val items: List<TopHeadLinesItem> = emptyList(),
    val selectedCategory: CategoryType = CategoryType.General,
    val isError: Boolean = false
) {
    companion object {
        val Empty = TopHeadLinesState()
    }
}