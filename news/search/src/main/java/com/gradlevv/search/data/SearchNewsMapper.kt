package com.gradlevv.search.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.search.data.model.SearchResponse
import com.gradlevv.search.domain.SearchNewsItem
import javax.inject.Inject

class SearchNewsMapper @Inject constructor(
    private val mapper: SearchNewsItemMapper
) : BaseDataMapper<SearchResponse, List<SearchNewsItem>> {

    override fun mapTo(from: SearchResponse): List<SearchNewsItem> {
        from.articleList ?: return emptyList()
        return from.articleList.map(mapper::mapTo)
    }
}