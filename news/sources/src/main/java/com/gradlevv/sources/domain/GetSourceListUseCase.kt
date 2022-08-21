package com.gradlevv.sources.domain

import com.gradlevv.core.data.model.Result
import javax.inject.Inject

class GetSourceListUseCase @Inject constructor(
    private val repository: SourcesRepository
) {
    suspend operator fun invoke(): Result<List<SourceItemDomainModel>> {
        return repository.getSourceList()
    }
}