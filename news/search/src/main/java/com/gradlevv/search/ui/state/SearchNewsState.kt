package com.gradlevv.search.ui.state

import com.gradlevv.search.domain.SearchNewsItem

data class SearchNewsState(
    val loadingType: LoadingType = LoadingType.NONE,
    val items: List<SearchNewsItem> = emptyList(),
    val isError: Boolean = false
){
    companion object {
        val Empty = SearchNewsState()
    }

    val isSearching
        get() = loadingType == LoadingType.SEARCH

    val isInitialLoading
        get() = loadingType == LoadingType.INITIAL
}

enum class LoadingType {
    NONE,
    INITIAL,
    SEARCH
}