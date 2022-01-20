package com.gradlevv.core

import di.CoreComponent
import di.DaggerCoreComponent

interface CoreComponentProvider {
    fun coreComponent() : CoreComponent
}

abstract class CoreApp : CoreComponentProvider {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }
}