package com.gradlevv.core.di

import androidx.lifecycle.ViewModelProvider
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.IntentUtilsImpl
import dagger.Binds
import dagger.Module


@Module
abstract class BinderModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindIntentUtils(intentUtilsImpl: IntentUtilsImpl): IntentUtils
}