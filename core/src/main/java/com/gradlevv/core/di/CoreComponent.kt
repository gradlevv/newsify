package com.gradlevv.core.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {

    fun provideApplication(): Application
    fun contex(): Context

    @Component.Builder
    interface Builder{
        fun application(@BindsInstance application: Application): Builder
        fun build(): CoreComponent
    }
}