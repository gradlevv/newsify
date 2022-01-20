package di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object CoreModule {

    @Provides
    fun provideContext(application: Application) : Context = application
}