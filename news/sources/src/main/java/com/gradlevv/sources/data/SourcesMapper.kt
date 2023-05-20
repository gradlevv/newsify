package com.gradlevv.sources.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.sources.data.model.SourcesResponse
import com.gradlevv.sources.domain.model.SourceItem
import javax.inject.Inject

class SourcesMapper @Inject constructor(
    private val mapper: SourcesItemMapper
) : BaseDataMapper<SourcesResponse, List<SourceItem>> {

    override fun mapTo(from: SourcesResponse): List<SourceItem> {
        return from.sourceList?.mapNotNull(mapper::mapTo).orEmpty()
    }
}