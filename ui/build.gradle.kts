plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.gradlevv.newsify.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation(project(":core"))

    // Constraint Layout
    api(libs.constraintLayout)
    api(libs.material)
    api(libs.appcompat)
    implementation(libs.androidx.material)
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material3)

    api(libs.glide)
    kapt(libs.glideCompiler)

    implementation(libs.navigationUiKtx)
    implementation(libs.navigationFragmentKtx)
}