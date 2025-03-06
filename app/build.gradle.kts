plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safe.args)
}

android {
    compileSdk = project.extra["build.compiledSdkVersion"] as Int

    defaultConfig {
        applicationId = "com.gradlevv.newsify"
        minSdk = project.extra["build.minSdkVersion"] as Int
        targetSdk = project.extra["build.targetSdkVersion"] as Int
        versionCode = project.extra["build.versionCode"] as Int
        versionName = project.extra["build.versionName"] as String

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":news:list"))
    implementation(project(":news:sources"))
    implementation(project(":news:search"))
    implementation(project(":news:setting"))

    kapt(libs.daggerCompiler)
} 