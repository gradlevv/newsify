package com.gradlevv.newsify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.Timer

@HiltAndroidApp
class MainApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}