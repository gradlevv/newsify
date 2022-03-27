package com.gradlevv.core.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [CoreModule::class, CoreNetworkModule::class])
@Singleton
interface CoreComponent {

    fun provideApplication(): Application
    fun context(): Context

    fun provideRetrofit(): Retrofit

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun build(): CoreComponent
    }
}