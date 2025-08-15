package com.gradlevv.list.di

import com.gradlevv.list.data.source.NewsListRepositoryImpl
import com.gradlevv.list.data.source.NewsListService
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.list.domain.usecase.GetTopHeadLinesUseCase
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
    abstract fun bindNewsListRepository(newsListRepositoryImpl: NewsListRepositoryImpl) : NewsListRepository

    companion object {

        @Provides
        fun provideNewsListService(retrofit: Retrofit): NewsListService {
            return retrofit.create(NewsListService::class.java)
        }

        @Provides
        fun provideGetCategoryTypeUseCase(repository: NewsListRepository) : GetCategoryTypeUseCase {
            return GetCategoryTypeUseCase(repository::getCategoryList)
        }

        @Provides
        fun provideGetTopHeadLinesUseCase(repository: NewsListRepository): GetTopHeadLinesUseCase {
            return GetTopHeadLinesUseCase(repository::getTopHeadLines)
        }
    }
}

