plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.1.20"
}

android {
    namespace = "com.gradlevv.newsify.news.list"
    resourcePrefix = "news_list_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.fragment)
    kapt(libs.hilt.android.compiler)

    implementation(libs.viewmodel)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleruntimeKtx)
    implementation(libs.lifecycleCommonJava8)

    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)

    implementation(platform(libs.compose.bom))
    implementation(libs.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

}

kapt {
    correctErrorTypes = true
}