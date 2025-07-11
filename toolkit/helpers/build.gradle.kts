plugins {
    alias(libs.plugins.toolkit.feature)
}

android {
    namespace = "com.vald3nir.toolkit.helpers"
}

dependencies {
    implementation(project(":toolkit:firebase"))
}