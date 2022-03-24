package com.gradlevv.list.di

import com.gradlevv.list.data.source.NewsListService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object NewsListModule {

    @Provides
    fun provideNewsListService(retrofit: Retrofit): NewsListService {
        return retrofit.create(NewsListService::class.java)
    }
}