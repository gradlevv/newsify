package com.gradlevv.core

import android.app.Application
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.core.di.DaggerCoreComponent

interface CoreComponentProvider {
    fun coreComponent() : CoreComponent
}

abstract class CoreApp : Application(),CoreComponentProvider {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    override fun coreComponent() = coreComponent
}