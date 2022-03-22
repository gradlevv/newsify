package com.gradlevv.setting.di

import androidx.lifecycle.ViewModel
import com.gradlevv.core.di.ViewModelKey
import com.gradlevv.setting.ui.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel

}

