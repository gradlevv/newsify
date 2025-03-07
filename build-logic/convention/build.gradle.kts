plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "newsify.android.application"
            implementationClass = "AndroidApplicationPlugin"
            description = "Android Application plugin for Newsify"
        }
        create("androidLibrary") {
            id = "newsify.android.library"
            implementationClass = "AndroidLibraryPlugin"
            description = "Android Library plugin for Newsify"
        }
    }
} 