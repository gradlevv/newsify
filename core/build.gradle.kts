plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt)
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
    implementation(libs.okhttp)
    implementation(libs.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.appcompat)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)

    implementation(libs.timber)
    api(libs.threetenabp)
}

kapt {
    correctErrorTypes = true
}