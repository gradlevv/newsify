package com.gradlevv.list.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.core.util.DateTimeHelper.simpleDateFormat
import com.gradlevv.list.data.model.TopHeadLinesResponse
import com.gradlevv.list.domain.TopHeadLinesItem
import javax.inject.Inject

class TopHeadLinesItemMapper @Inject constructor() :
    BaseDataMapper<TopHeadLinesResponse.Article, TopHeadLinesItem> {

    override fun mapTo(from: TopHeadLinesResponse.Article): TopHeadLinesItem {
        return TopHeadLinesItem(
            source = TopHeadLinesItem.SourceItem(
                id = from.source?.id ?: "",
                name = from.source?.name ?: ""
            ),
            author = from.author ?: "",
            title = from.title ?: "",
            description = from.description ?: "",
            url = from.url ?: "",
            imageUrl = from.urlToImage ?: "",
            publishedAt = simpleDateFormat(from.publishedAt ?: "") ?: "",
            content = from.content ?: ""
        )
    }
}