package com.toolkit.plugs

import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.util.Properties


const val debugName = "debug"
const val releaseName = "release"

fun Project.setupBuildTypes() {
    extensions.configure<ApplicationExtension> {
        buildTypes.apply {
            maybeCreate(debugName)
            getByName(debugName) {
                applicationIdSuffix = ".${debugName}"
                isMinifyEnabled = false
                isDebuggable = true
            }
            maybeCreate(releaseName)
            getByName(releaseName) {
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}

fun Project.setupProductFlavors() {
    extensions.configure<ApplicationExtension> {
        flavorDimensions += "environment"
        productFlavors {
            maybeCreate("dev").apply {
                dimension = "environment"
                applicationIdSuffix = ".dev"
                versionNameSuffix = "-dev"
            }
            maybeCreate("prod").apply {
                dimension = "environment"
            }
        }
    }
}


fun Project.setupSigningConfigs(pathKeyStore: String, keyAlias: String, keyPassword: String, storePassword: String) {
    extensions.configure<ApplicationExtension> {
        val storeFilePath = File(pathKeyStore)
        buildTypes.apply {
            signingConfigs {
                getByName(debugName) { inputParam(storeFilePath = storeFilePath, keyAlias = keyAlias, keyPassword = keyPassword, storePassword = storePassword) }
                create(releaseName) { inputParam(storeFilePath = storeFilePath, keyAlias = keyAlias, keyPassword = keyPassword, storePassword = storePassword) }
            }
        }
    }
}

private fun ApkSigningConfig.inputParam(storeFilePath: File, keyAlias: String, keyPassword: String, storePassword: String) {
    this.keyAlias = keyAlias
    this.keyPassword = keyPassword
    this.storeFile = storeFilePath
    this.storePassword = storePassword
}

fun getEnvParameter(envFilePath: String, key: String, defaultValue: String = ""): String {
    val envFile = File(envFilePath)
    if (!envFile.exists()) return defaultValue
    val props = Properties().apply {
        envFile.inputStream().use { load(it) }
    }
    return props.getProperty(key, defaultValue)
}