package com.gradlevv.sources.domain

import com.gradlevv.core.data.model.Resource
import javax.inject.Inject

class GetSourceListUseCase @Inject constructor(
    private val repository: SourcesRepository
) {
    suspend operator fun invoke(): Resource<List<SourceItemDomainModel>> {
        return repository.getSourceList()
    }
}