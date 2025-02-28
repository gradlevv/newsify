// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    apply(from = "$rootDir/buildScript/libraryVersions.gradle")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${project.extra["app.gradle"]}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra["app.kotlin"]}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${project.extra["libraryVersion.navigationVersion"]}")
    }
}

allprojects {
    apply(from = "$rootDir/buildScript/dependencies.gradle")
    repositories {
        maven { url = uri("https://maven.google.com") }
        google()
        jcenter()
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