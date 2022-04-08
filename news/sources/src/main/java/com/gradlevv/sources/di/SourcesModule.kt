package com.gradlevv.sources.di

import com.gradlevv.sources.data.source.SourcesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object SourcesModule {

    @Provides
    fun provideSourcesService(retrofit: Retrofit): SourcesService {
        return retrofit.create(SourcesService::class.java)
    }
}