package com.gradlevv.sources.di

import com.gradlevv.sources.data.source.SourcesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object SourcesModule {

    @Provides
    fun provideSourcesService(retrofit: Retrofit): SourcesService {
        return retrofit.create(SourcesService::class.java)
    }
}