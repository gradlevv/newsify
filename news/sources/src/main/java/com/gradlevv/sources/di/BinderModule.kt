package com.gradlevv.sources.di

import com.gradlevv.sources.data.source.SourcesRepositoryImpl
import com.gradlevv.sources.data.source.SourcesService
import com.gradlevv.sources.domain.repository.SourcesRepository
import com.gradlevv.sources.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.sources.domain.usecase.GetSourceListUseCase
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

        @Provides
        fun provideGetCategoryTypeUseCase(repository: SourcesRepository): GetCategoryTypeUseCase {
            return GetCategoryTypeUseCase(repository::getCategoryList)
        }

        @Provides
        fun provideGetSourceListUseCase(repository: SourcesRepository): GetSourceListUseCase {
            return GetSourceListUseCase(repository::getSourceList)
        }
    }
}

