package com.gradlevv.core.di

import android.app.Application
import android.content.Context
import com.gradlevv.core.util.DateTimeHelper
import com.gradlevv.core.util.IntentUtils
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [CoreModule::class, CoreNetworkModule::class, BinderModule::class])
@Singleton
interface CoreComponent {

    fun provideApplication(): Application
    fun context(): Context

    fun provideRetrofit(): Retrofit
    fun provideIntentUtils(): IntentUtils
    fun provideDateTimeHelper(): DateTimeHelper

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun build(): CoreComponent
    }
}