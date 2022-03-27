package com.gradlevv.list.ui.state

import com.gradlevv.list.domain.TopHeadLinesItem

data class TopHeadLinesState(
    val isLoading: Boolean = false,
    val items: List<TopHeadLinesItem> = emptyList(),
    val isError: Boolean = false
)