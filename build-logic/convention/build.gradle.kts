plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.navigation.safeargs)
}

gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "newsify.android.application"
            implementationClass = "NewsifyAndroidApplicationPlugin"
            description = "Android Application plugin for Newsify"
        }
        create("androidLibrary") {
            id = "newsify.android.library"
            implementationClass = "NewsifyAndroidLibraryPlugin"
            description = "Android Library plugin for Newsify"
        }
    }
} 