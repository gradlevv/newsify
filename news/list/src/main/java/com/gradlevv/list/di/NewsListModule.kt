package com.gradlevv.list.di

import com.gradlevv.list.data.source.NewsListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit


@InstallIn(ViewModelComponent::class)
@Module
object NewsListModule {

    @Provides
    fun provideNewsListService(retrofit: Retrofit): NewsListService {
        return retrofit.create(NewsListService::class.java)
    }
}