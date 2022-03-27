package com.gradlevv.list.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.list.data.model.TopHeadLinesResponse
import com.gradlevv.list.domain.TopHeadLinesItem
import javax.inject.Inject

class TopHeadLinesMapper @Inject constructor(
    private val mapper: TopHeadLinesItemMapper
) : BaseDataMapper<TopHeadLinesResponse, List<TopHeadLinesItem>> {

    override fun mapTo(from: TopHeadLinesResponse): List<TopHeadLinesItem> {
        from.articleList ?: return emptyList()
        return from.articleList.map(mapper::mapTo)
    }
}