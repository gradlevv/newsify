plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.gradlevv.newsify.core"
}

dependencies {
    kapt(libs.daggerCompiler)

    api(libs.coroutineCore)
    api(libs.coroutineAndroid)
}