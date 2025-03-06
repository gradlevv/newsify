plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}


dependencies {
    kapt(daggerCompiler)

    api(coroutineCore)
    api(coroutineAndroid)
}