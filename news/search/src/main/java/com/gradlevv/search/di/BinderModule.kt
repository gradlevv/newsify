package com.gradlevv.search.di

import androidx.lifecycle.ViewModel
import com.gradlevv.core.di.ViewModelKey
import com.gradlevv.search.data.source.SearchNewsRepositoryImpl
import com.gradlevv.search.domain.SearchNewsRepository
import com.gradlevv.search.ui.SearchNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchNewsViewModel::class)
    abstract fun bindSearchNewViewModel(searchNewsViewModel: SearchNewsViewModel): ViewModel

    @Binds
    abstract fun bindSearchNewsRepository(searchNewsRepositoryImpl: SearchNewsRepositoryImpl): SearchNewsRepository

}

