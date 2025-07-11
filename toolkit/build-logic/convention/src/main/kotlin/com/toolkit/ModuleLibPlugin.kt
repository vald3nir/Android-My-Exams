import com.android.build.gradle.LibraryExtension
import com.toolkit.plugs.EnvironmentSetup
import com.toolkit.plugs.configureKotlinAndroid
import com.toolkit.plugs.setupBaseUtilsLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ModuleLibPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-parcelize")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("kotlin-parcelize")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.apply {
                    targetSdk = EnvironmentSetup.TARGET_SDK
                }
                packaging {
                    resources {
                        excludes += "META-INF/*"
                    }
                }
            }
            setupBaseUtilsLibs()
        }
    }
}