plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.gradlevv.newsify.news.search"
    resourcePrefix = "search_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))
}