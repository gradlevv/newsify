package com.gradlevv.sources.ui.state

import com.gradlevv.sources.domain.model.SourceItemDomainModel

data class NewsSourceState(
    val isLoading: Boolean = false,
    val items: List<SourceItemDomainModel> = emptyList(),
    val isError: Boolean = false
) {
    companion object {
        val Empty = NewsSourceState()
    }
}