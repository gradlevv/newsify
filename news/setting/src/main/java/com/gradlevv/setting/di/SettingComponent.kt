package com.gradlevv.setting.di

import com.gradlevv.core.di.AppScope
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.setting.ui.SettingFragment
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [BinderModule::class]
)
@AppScope
interface SettingComponent {

    fun inject(fragment: SettingFragment)

   @Component.Factory
   interface Builder{
       fun create(coreComponent: CoreComponent): SettingComponent
   }
}