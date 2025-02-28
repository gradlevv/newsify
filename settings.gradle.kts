include(":app")
include(":core")
include(":ui")
include(":news:list")
include(":news:sources")
include(":news:search")
include(":news:setting")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
} 