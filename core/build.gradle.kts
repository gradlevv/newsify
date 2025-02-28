plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

apply(from = "$rootDir/buildScript/libraryVersions.gradle.kts")

dependencies {
    kapt(daggerCompiler)

    api(coroutineCore)
    api(coroutineAndroid)
}