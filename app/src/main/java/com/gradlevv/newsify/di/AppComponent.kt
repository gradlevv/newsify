package com.gradlevv.newsify.di

import com.gradlevv.core.di.CoreComponent
import com.gradlevv.newsify.MainApp
import com.gradlevv.newsify.ui.MainActivity
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@MainScope
interface AppComponent {

    fun inject(app: MainApp)
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }
}