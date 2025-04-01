plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gradlevv.newsify.news.search"
    resourcePrefix = "search_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.viewmodel)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleruntimeKtx)
    implementation(libs.lifecycleCommonJava8)

    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)
}