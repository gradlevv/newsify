package com.gradlevv.sources.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.sources.data.model.SourcesResponse
import com.gradlevv.sources.domain.SourceItemDomainModel
import javax.inject.Inject

class SourcesItemMapper @Inject constructor() :
    BaseDataMapper<SourcesResponse.SourceItem, SourceItemDomainModel> {

    override fun mapTo(from: SourcesResponse.SourceItem): SourceItemDomainModel {
        return SourceItemDomainModel(
            name = from.name ?: "",
            description = from.description ?: "",
            url = from.url ?: "https://www.google.com",
            category = from.category ?: "",
            language = from.language ?: "",
            country = from.country ?: ""
        )
    }
}