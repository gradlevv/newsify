package com.gradlevv.favorite.di

import com.gradlevv.core.di.AppScope
import com.gradlevv.core.di.CoreComponent
import com.gradlevv.favorite.ui.FavoriteNewsFragment
import dagger.Component

@Component(
    dependencies = [CoreComponent::class],
    modules = [BinderModule::class]
)
@AppScope
interface FavoriteNewsComponent {

    fun inject(fragment: FavoriteNewsFragment)

   @Component.Factory
   interface Builder{
       fun create(coreComponent: CoreComponent): FavoriteNewsComponent
   }
}