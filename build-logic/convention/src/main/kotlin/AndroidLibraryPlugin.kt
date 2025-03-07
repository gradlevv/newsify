import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.LibraryExtension

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("androidx.navigation.safeargs.kotlin")
            }

            extensions.configure<LibraryExtension>("android") {
                compileSdk = 34 // Replace with your build.compiledSdkVersion

                defaultConfig {
                    targetSdk = 34 // Replace with your build.targetSdkVersion
                    minSdk = 24 // Replace with your build.minSdkVersion
                    versionCode = 1 // Replace with your app.versionCode
                    versionName = "1.0" // Replace with your app.versionName
                    vectorDrawables.useSupportLibrary = true
                }

                buildTypes {
                    debug {
                        isMinifyEnabled = false
                    }
                    release {
                        isMinifyEnabled = true
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
        }
    }
} 