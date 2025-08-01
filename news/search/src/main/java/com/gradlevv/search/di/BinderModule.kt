package com.gradlevv.search.di

import com.gradlevv.search.data.source.SearchNewsRepositoryImpl
import com.gradlevv.search.data.source.SearchNewsService
import com.gradlevv.search.domain.SearchNewsRepository
import com.gradlevv.search.util.DateProvider
import com.gradlevv.search.util.DateProviderImpl
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
    abstract fun bindSearchNewsRepository(impl: SearchNewsRepositoryImpl): SearchNewsRepository

    @Binds
    abstract fun bindDateProvider(impl: DateProviderImpl): DateProvider

    companion object {
        @Provides
        fun provideSearchNewsService(retrofit: Retrofit): SearchNewsService {
            return retrofit.create(SearchNewsService::class.java)
        }
    }
}

