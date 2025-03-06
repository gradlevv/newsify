plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core"))

    // Constraint Layout
    api(constraintLayout)

    api(glide)
    kapt(glideCompiler)
}