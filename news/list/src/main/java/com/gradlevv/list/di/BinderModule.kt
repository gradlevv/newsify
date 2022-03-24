package com.gradlevv.list.di

import androidx.lifecycle.ViewModel
import com.gradlevv.core.di.ViewModelKey
import com.gradlevv.list.domain.NewsListRepository
import com.gradlevv.list.data.source.NewsListRepositoryImpl
import com.gradlevv.list.ui.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewListViewModel(newsListViewModel: NewsListViewModel): ViewModel

    @Binds
    abstract fun bindNewsListRepository(newsListRepositoryImpl: NewsListRepositoryImpl) : NewsListRepository
}

