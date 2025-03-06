plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

dependencies {
    kapt(libs.daggerCompiler)

    api(libs.coroutineCore)
    api(libs.coroutineAndroid)
}