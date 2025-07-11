import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.toolkit.plugs"

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_19.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.androidx.room.gradle.plugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("ApplicationPlugin") {
            id = "toolkit.plug.application"
            implementationClass = "ApplicationPlugin"
        }
        register("ModuleFeaturePlugin") {
            id = "toolkit.plug.feature"
            implementationClass = "ModuleFeaturePlugin"
        }
        register("ModuleLibPlugin") {
            id = "toolkit.plug.lib"
            implementationClass = "ModuleLibPlugin"
        }
        register("HiltPlugin") {
            id = "toolkit.plug.hilt"
            implementationClass = "HiltPlugin"
        }
        register("RoomPlugin") {
            id = "toolkit.plug.room"
            implementationClass = "RoomPlugin"
        }
        register("FirebasePlugin") {
            id = "toolkit.plug.firebase"
            implementationClass = "FirebasePlugin"
        }
    }
}