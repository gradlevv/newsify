package com.gradlevv.list.di

import com.gradlevv.core.di.AppScope
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.list.ui.NewsListFragment
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [BinderModule::class]
)
@AppScope
interface NewsListComponent {

    fun inject(fragment: NewsListFragment)

   @Component.Factory
   interface Builder{
       fun create(coreComponent: CoreComponent): NewsListComponent
   }
}