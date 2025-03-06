plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safe.args)
}

android {
    resourcePrefix = "news_list_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    kapt(libs.daggerCompiler)
}