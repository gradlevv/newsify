package com.gradlevv.list.di

import com.gradlevv.list.data.source.NewsListRepositoryImpl
import com.gradlevv.list.domain.NewsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class BinderModule {

    @Binds
    abstract fun bindNewsListRepository(newsListRepositoryImpl: NewsListRepositoryImpl) : NewsListRepository
}

