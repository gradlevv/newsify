package com.gradlevv.search.di


import com.gradlevv.search.data.source.SearchNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object SearchNewsModule {

    @Provides
    fun provideSearchNewsService(retrofit: Retrofit): SearchNewsService {
        return retrofit.create(SearchNewsService::class.java)
    }
}