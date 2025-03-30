pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":app")
include(":core")
include(":ui")
include(":news:list")
include(":news:sources")
include(":news:search")
include(":news:setting")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
} 