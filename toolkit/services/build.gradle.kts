plugins {
    alias(libs.plugins.toolkit.lib)
}

android {
    namespace = "com.vald3nir.toolkit.service"
}

dependencies {
    implementation(libs.play.services.location)
    implementation(libs.androidx.work.runtime.ktx)
}