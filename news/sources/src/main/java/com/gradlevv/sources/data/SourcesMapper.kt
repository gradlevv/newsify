package com.gradlevv.sources.data

import com.gradlevv.core.mapper.BaseDataMapper
import com.gradlevv.sources.data.model.SourcesResponse
import com.gradlevv.sources.domain.SourceItemDomainModel
import javax.inject.Inject

class SourcesMapper @Inject constructor(
  private val mapper: SourcesItemMapper
) : BaseDataMapper<SourcesResponse, List<SourceItemDomainModel>> {

    override fun mapTo(from: SourcesResponse): List<SourceItemDomainModel> {
        from.sourceList ?: return emptyList()
        return from.sourceList.map(mapper::mapTo)
    }
}