import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class NewsifyAndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("androidx.navigation.safeargs.kotlin")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = 34

                defaultConfig {
                    minSdk = 24
                    targetSdk = 34
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
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                jvmToolchain(17)
            }
        }
    }
} 