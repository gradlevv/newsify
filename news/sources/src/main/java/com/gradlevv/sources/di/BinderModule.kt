package com.gradlevv.sources.di

import androidx.lifecycle.ViewModel
import com.gradlevv.core.di.ViewModelKey
import com.gradlevv.sources.ui.NewsSourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsSourcesViewModel::class)
    abstract fun bindNewsSourcesViewModel(newsSourcesViewModel: NewsSourcesViewModel): ViewModel

}

