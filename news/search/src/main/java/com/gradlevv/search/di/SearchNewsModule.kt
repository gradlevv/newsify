package com.gradlevv.search.di


import com.gradlevv.search.data.source.SearchNewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object SearchNewsModule {

    @Provides
    fun provideSearchNewsService(retrofit: Retrofit): SearchNewsService {
        return retrofit.create(SearchNewsService::class.java)
    }
}