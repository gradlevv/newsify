package com.gradlevv.sources.di

import com.gradlevv.sources.data.source.SourcesRepositoryImpl
import com.gradlevv.sources.domain.repository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class BinderModule {

    @Binds
    abstract fun bindNewsSourceRepository(sourcesRepositoryImpl: SourcesRepositoryImpl): SourcesRepository
}

