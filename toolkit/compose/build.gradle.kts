plugins {
    alias(libs.plugins.toolkit.feature)
    alias(libs.plugins.toolkit.hilt)
}

android {
    namespace = "com.vald3nir.toolkit.compose"
}

dependencies {
    api(project(":toolkit:helpers"))

    // https://github.com/valentinilk/compose-shimmer
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.2")
}