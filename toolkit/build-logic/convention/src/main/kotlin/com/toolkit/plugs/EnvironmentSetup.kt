package com.toolkit.plugs

import org.gradle.api.JavaVersion

// SDK e Java
object EnvironmentSetup {
    const val MIN_SDK = 26
    const val TARGET_SDK = 35
    const val COMPILE_SDK = 35
    val JAVA_VERSION = JavaVersion.VERSION_19
}

// Compose & UI
const val composeBomVersion = "2023.10.01"
const val composeGraphicsVersion = "1.5.4"
const val composeToolingPreviewVersion = "1.5.4"
const val runtimeLivedataVersion = "1.8.3"
const val composeUiTestManifestVersion = "1.5.4"
const val composeUiToolingVersion = "1.5.4"
const val composeUiVersion = "1.8.0"

// Core AndroidX
const val activityComposeVersion = "1.7.2"
const val activityVersion = "1.10.1"
const val appCompat = "1.6.1"
const val coreKtxVersion = "1.12.0"
const val lifecycleVersion = "2.8.7"
const val navigationComposeVersion = "2.8.8"

// Material Design
const val materialVersion = "1.5.4"
const val materialIconsExtendedVersion = "1.7.8"
const val material3Version = "1.1.2"

// Coil (Image Loader)
// https://github.com/coil-kt/coil
const val coilCompose = "3.1.0"
const val coilNetwork = "3.1.0"

// Room
const val roomVersion = "2.6.1"

// Hilt
const val hiltVersion = "2.54"
const val hiltNavigationCompose = "1.2.0"

// Serialization & JSON
const val kotlinxSerialization = "1.8.0"
const val gsonVersion = "2.11.0"

// Firebase and Credentials
const val firebaseBom = "33.16.0"
const val credentialsVersion = "1.3.0"
const val googleidCredentialsVersion = "1.1.1"
const val googlePlayServiceGMSVersion = "20.7.0"

// Testes
const val junitVersion = "4.13.2"
const val androidJunitVersion = "1.1.5"
const val espressoCoreVersion = "3.5.1"


