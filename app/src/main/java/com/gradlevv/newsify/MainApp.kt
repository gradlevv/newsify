package com.gradlevv.newsify

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.Timer

@HiltAndroidApp
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        AndroidThreeTen.init(this)
    }

}