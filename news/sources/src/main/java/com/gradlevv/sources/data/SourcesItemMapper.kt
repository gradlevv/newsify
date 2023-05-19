package com.gradlevv.sources.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.sources.data.model.SourcesResponse
import com.gradlevv.sources.domain.SourceItemDomainModel
import javax.inject.Inject

class SourcesItemMapper @Inject constructor() :
    BaseDataMapper<SourcesResponse.SourceItem, SourceItemDomainModel?> {

    override fun mapTo(from: SourcesResponse.SourceItem): SourceItemDomainModel? {
        if (from.name.isNullOrEmpty() ||
            from.description.isNullOrEmpty() ||
            from.url.isNullOrEmpty() ||
            from.category.isNullOrEmpty() ||
            from.language.isNullOrEmpty() ||
            from.country.isNullOrEmpty()
        ) return null

        return SourceItemDomainModel(
            name = from.name,
            description = from.description,
            url = from.url,
            category = from.category,
            language = from.language,
            country = from.country
        )
    }
}