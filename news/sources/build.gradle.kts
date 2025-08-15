plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.gradlevv.newsify.news.sources"
    resourcePrefix = "sources_"
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
    implementation(libs.material3)

    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)

    implementation(platform(libs.compose.bom))
    implementation(libs.navigation.compose)
    implementation(libs.ui.tooling)


    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}