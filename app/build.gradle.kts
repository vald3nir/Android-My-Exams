import com.toolkit.plugs.getEnvParameter
import com.toolkit.plugs.setupSigningConfigs

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.appdistribution)
    alias(libs.plugins.google.firebase.crashlytics)
}

// todo replace for your .env file path
val envFilePath = "D:\\Documents\\GitHub\\Documents\\environments\\my-exams.env"
val pathKeyStore = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PATH")
val keyAlias = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_ALIAS")
val keyPassword = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PASSWORD")
val storePassword = getEnvParameter(envFilePath = envFilePath, key = "STORE_PASSWORD")
val serverClientID = getEnvParameter(envFilePath = envFilePath, key = "SERVER_CLIENT_ID")



android {
    namespace = "com.vald3nir.myexams"
    defaultConfig {
        applicationId = namespace
        versionCode = 1
        versionName = "2025.1.0"

        setupSigningConfigs(
            pathKeyStore = pathKeyStore,
            keyAlias = keyAlias,
            keyPassword = keyPassword,
            storePassword = storePassword
        )

        buildConfigField("String", "SERVER_CLIENT_ID", serverClientID)
        buildConfigField("int", "DB_VERSION", versionCode.toString())
    }
}

dependencies {
    implementation(project(":toolkit:compose"))
    implementation(project(":toolkit:helpers"))
    implementation(project(":toolkit:networking"))
    implementation(project(":toolkit:firebase"))
    implementation(project(":toolkit:autentication"))
    implementation(libs.firebase.crashlytics)
}