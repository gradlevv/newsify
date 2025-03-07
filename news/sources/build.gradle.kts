plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gradlevv.newsify.news.sources"
    resourcePrefix = "sources_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    kapt(daggerCompiler)
}