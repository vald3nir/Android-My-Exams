buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.2")
        classpath("com.google.firebase:firebase-appdistribution-gradle:5.0.0")
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
}