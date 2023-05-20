package com.gradlevv.sources.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.model.SourceItemDomainModel
import com.gradlevv.sources.domain.repository.SourcesRepository
import javax.inject.Inject

class GetSourceListUseCase @Inject constructor(
    private val repository: SourcesRepository
) {
    suspend operator fun invoke(): Result<List<SourceItemDomainModel>> {
        return repository.getSourceList()
    }
}