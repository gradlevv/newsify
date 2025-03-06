plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

dependencies {
    implementation(project(":core"))

    // Constraint Layout
    api(libs.constraintLayout)

    api(libs.glide)
    kapt(libs.glideCompiler)
}