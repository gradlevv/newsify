package com.gradlevv.search.di

import com.gradlevv.core.di.AppScope
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.search.ui.SearchNewsFragment
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [BinderModule::class,SearchNewsModule::class]
)
@AppScope
interface SearchNewsComponent {

    fun inject(fragment: SearchNewsFragment)

   @Component.Factory
   interface Builder{
       fun create(coreComponent: CoreComponent): SearchNewsComponent
   }
}