package com.gradlevv.setting.data.repo

import com.gradlevv.setting.domain.repo.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor() : SettingRepository {

    override fun getUserTheme(): String {
        return ""
    }

    override fun setUserSelectedTheme(value: Boolean) {

    }
}