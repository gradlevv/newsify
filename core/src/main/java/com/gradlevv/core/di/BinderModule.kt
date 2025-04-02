package com.gradlevv.core.di

import com.gradlevv.core.util.IntentUtils
import com.gradlevv.core.util.IntentUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class BinderModule {

    @Binds
    abstract fun bindIntentUtils(intentUtilsImpl: IntentUtilsImpl): IntentUtils
}