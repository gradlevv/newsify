plugins {
    id("newsify.android.library")
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

    implementation(libs.appcompat)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okhttp)
    implementation(libs.timber)
    implementation(libs.interceptor)
    implementation(libs.dagger)
    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)
    implementation(libs.fragmentKtx)
    implementation(libs.lifecycleruntimeKtx)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleCommonJava8)
    implementation(libs.livedataKtx)
    implementation(libs.viewmodel)
    implementation(libs.coreKtx)
}