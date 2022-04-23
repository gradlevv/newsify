package com.gradlevv.core.di

import android.app.Application
import android.content.Context
import com.gradlevv.core.util.*
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    modules = [CoreModule::class, CoreNetworkModule::class,
        BinderModule::class, CoroutineDispatcherModule::class]
)
@Singleton
interface CoreComponent {

    fun provideApplication(): Application
    fun context(): Context

    fun provideRetrofit(): Retrofit
    fun provideIntentUtils(): IntentUtils

    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher

    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun build(): CoreComponent
    }
}