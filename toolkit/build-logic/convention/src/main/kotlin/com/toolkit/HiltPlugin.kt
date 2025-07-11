import com.toolkit.plugs.hiltNavigationCompose
import com.toolkit.plugs.hiltVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.google.devtools.ksp")
            apply("com.google.dagger.hilt.android")
        }
        dependencies {
            add("ksp", "com.google.dagger:hilt-compiler:$hiltVersion")
            add("implementation", "com.google.dagger:hilt-core:$hiltVersion")
            add("implementation", "androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose")
            add("implementation", "com.google.dagger:hilt-android:$hiltVersion")
        }
    }
}
