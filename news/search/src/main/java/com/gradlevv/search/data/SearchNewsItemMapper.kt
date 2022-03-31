package com.gradlevv.search.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.search.data.model.SearchResponse
import com.gradlevv.search.domain.SearchNewsItem
import javax.inject.Inject

class SearchNewsItemMapper @Inject constructor() :
    BaseDataMapper<SearchResponse.Article, SearchNewsItem> {

    override fun mapTo(from: SearchResponse.Article): SearchNewsItem {
        return SearchNewsItem(
            source = SearchNewsItem.SourceItem(
                id = from.source?.id ?: "",
                name = from.source?.name ?: ""
            ),
            author = from.author ?: "",
            title = from.title ?: "",
            description = from.description ?: "",
            url = from.url ?: "",
            imageUrl = from.urlToImage ?: "",
            publishedAt = from.publishedAt ?: "",
            content = from.content ?: ""
        )
    }
}