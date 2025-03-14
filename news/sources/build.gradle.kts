plugins {
    id("newsify.android.library")
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.gradlevv.newsify.news.sources"
    resourcePrefix = "sources_"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))
}