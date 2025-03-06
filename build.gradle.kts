// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.navigation.safe.args) apply false
}

allprojects {
    repositories {
        maven { url = uri("https://maven.google.com") }
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

apply(from = "$rootDir/buildScript/subModules.gradle")

dependencies {
    implementation(libs.kotlinStd8)
    implementation(libs.material)
    // ... other dependencies
} 