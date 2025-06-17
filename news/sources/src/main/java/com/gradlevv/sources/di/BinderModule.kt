package com.gradlevv.sources.di

import com.gradlevv.sources.data.source.SourcesRepositoryImpl
import com.gradlevv.sources.data.source.SourcesService
import com.gradlevv.sources.domain.repository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
abstract class BinderModule {

    @Binds
    abstract fun bindNewsSourceRepository(impl: SourcesRepositoryImpl): SourcesRepository

    companion object {
        @Provides
        fun provideSourcesService(retrofit: Retrofit): SourcesService {
            return retrofit.create(SourcesService::class.java)
        }
    }
}

