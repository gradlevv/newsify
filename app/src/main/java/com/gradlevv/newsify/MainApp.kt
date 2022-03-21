package com.gradlevv.newsify

import com.gradlevv.core.CoreApp
import com.gradlevv.newsify.di.AppComponent
import com.gradlevv.newsify.di.DaggerAppComponent

interface AppComponentProvider {
    fun appComponent(): AppComponent
}

class MainApp : CoreApp(),AppComponentProvider{

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent())
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    override fun appComponent() = appComponent

}