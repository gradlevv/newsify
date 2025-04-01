plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.gradlevv.newsify.ui"
}

dependencies {
    implementation(project(":core"))

    // Constraint Layout
    api(libs.constraintLayout)
    api(libs.material)
    api(libs.appcompat)

    api(libs.glide)
    kapt(libs.glideCompiler)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)
}