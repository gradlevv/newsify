plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
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
    implementation(libs.material3)
    implementation(platform(libs.compose.bom))
    implementation(libs.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}

kapt {
    correctErrorTypes = true
}