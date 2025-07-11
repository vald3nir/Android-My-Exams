plugins {
    alias(libs.plugins.toolkit.feature)
    alias(libs.plugins.toolkit.hilt)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.vald3nir.toolkit.auth"
}

dependencies {
    implementation(project(":toolkit:compose"))
    implementation(project(":toolkit:firebase"))
}