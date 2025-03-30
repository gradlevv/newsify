plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.gradlevv.newsify"
    compileSdk = libs.versions.compiledSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.gradlevv.newsify"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = libs.versions.patch.get().toInt()
        versionName = "${libs.versions.major.get()}.${libs.versions.minor.get()}.${libs.versions.patch.get()}"

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

    implementation(libs.navigationFragmentKtx)
    implementation(libs.navigationUiKtx)
    kapt(libs.daggerCompiler)
} 