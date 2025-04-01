plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gradlevv.newsify.core"
}

dependencies {

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    api(libs.coroutineCore)
    api(libs.coroutineAndroid)

    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okhttp)
    implementation(libs.interceptor)

    implementation(libs.appcompat)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)

    implementation(libs.timber)
}