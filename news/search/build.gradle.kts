plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    resourcePrefix = "search_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    kapt(daggerCompiler)
}