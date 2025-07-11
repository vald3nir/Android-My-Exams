buildscript {
    dependencies {
        classpath(libs.android.gradlePlugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.google.firebase.appdistribution) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false
}