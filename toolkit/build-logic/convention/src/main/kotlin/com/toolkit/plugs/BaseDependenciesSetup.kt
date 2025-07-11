package com.toolkit.plugs

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

fun Project.setupBaseLibsCompose() {
    dependencies {

        // Base Libs
        add("implementation", platform("androidx.compose:compose-bom:$composeBomVersion"))
        add("implementation", "androidx.activity:activity-compose:$activityComposeVersion")
        add("implementation", "androidx.navigation:navigation-compose:$navigationComposeVersion")
        androidCoreLibs()

        // UI & Material Design
        add("implementation", "androidx.compose.ui:ui:$composeUiVersion")
        add("implementation", "androidx.compose.ui:ui-graphics:$composeGraphicsVersion")
        add("implementation", "androidx.compose.ui:ui-tooling-preview:$composeToolingPreviewVersion")
        add("implementation", "androidx.compose.runtime:runtime-livedata:$runtimeLivedataVersion")
        add("implementation", "androidx.compose.material:material:$materialVersion")
        add("implementation", "androidx.compose.material3:material3:$material3Version")
        add("implementation", "androidx.compose.material:material-icons-extended:$materialIconsExtendedVersion")

        // Image Loader
        add("implementation", "io.coil-kt.coil3:coil-compose:$coilCompose")
        add("implementation", "io.coil-kt.coil3:coil-network-okhttp:$coilNetwork")

        // Just Debug
        add("debugImplementation", "androidx.compose.ui:ui-test-manifest:$composeUiTestManifestVersion")
        add("debugImplementation", "androidx.compose.ui:ui-tooling:$composeUiToolingVersion")
    }
}

private fun DependencyHandlerScope.androidCoreLibs() {
    add("implementation", "androidx.activity:activity-ktx:$activityVersion")
    add("implementation", "androidx.core:core-ktx:$coreKtxVersion")
    add("implementation", "androidx.navigation:navigation-compose:$navigationComposeVersion")
    add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    add("implementation", "androidx.lifecycle:lifecycle-service:$lifecycleVersion")

    // Test Libs
    add("testImplementation", "junit:junit:$junitVersion")
    add("androidTestImplementation", "androidx.test.ext:junit:$androidJunitVersion")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:$espressoCoreVersion")
}

fun Project.setupBaseUtilsLibs() {
    dependencies {
        androidCoreLibs()
        add("api", "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerialization")
        add("api", "com.google.code.gson:gson:$gsonVersion")
    }
}
