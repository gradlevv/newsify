package com.gradlevv.setting.domain.repo

interface SettingRepository {
    fun getUserTheme(): String
    fun setUserSelectedTheme(value: Boolean)
}