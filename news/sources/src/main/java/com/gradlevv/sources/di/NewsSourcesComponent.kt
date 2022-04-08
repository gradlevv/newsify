package com.gradlevv.sources.di

import com.gradlevv.core.di.AppScope
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.sources.ui.NewsSourcesFragment
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [BinderModule::class,SourcesModule::class]
)
@AppScope
interface NewsSourcesComponent {

    fun inject(sourcesFragment: NewsSourcesFragment)

   @Component.Factory
   interface Builder{
       fun create(coreComponent: CoreComponent): NewsSourcesComponent
   }
}