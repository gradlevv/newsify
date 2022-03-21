package com.gradlevv.favorite.di

import androidx.lifecycle.ViewModel
import com.gradlevv.core.di.ViewModelKey
import com.gradlevv.favorite.ui.FavoriteNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteNewsViewModel::class)
    abstract fun bindNewListViewModel(newsListViewModel: FavoriteNewsViewModel): ViewModel

}

