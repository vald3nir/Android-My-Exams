plugins {
    alias(libs.plugins.toolkit.lib)
}

android {
    namespace = "com.vald3nir.tolkit.networking"
}

dependencies {
    // MQTT Client
    api("com.hivemq:hivemq-mqtt-client:1.3.5")

    // HTTP Client
    api("com.squareup.retrofit2:retrofit:2.11.0")
    api("com.squareup.retrofit2:converter-gson:2.11.0")
    api("com.squareup.okhttp3:logging-interceptor:4.12.0")
}