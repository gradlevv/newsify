package com.gradlevv.list.ui.state

import com.gradlevv.list.domain.TopHeadLinesItem

data class TopHeadLinesState(
    val isLoading: Boolean = false,
    val items: List<TopHeadLinesItem> = emptyList(),
    val type: String = "general",
    val categoryName: Int = -1,
    val isError: Boolean = false
) {
    companion object {
        val Empty = TopHeadLinesState()
    }
}