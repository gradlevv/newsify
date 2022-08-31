package com.gradlevv.search.ui.state

import com.gradlevv.search.domain.SearchNewsItem

data class SearchNewsState(
    val isLoading: Boolean = false,
    val items: List<SearchNewsItem> = emptyList(),
    val isError: Boolean = false
){
    companion object {
        val Empty = SearchNewsState()
    }
}