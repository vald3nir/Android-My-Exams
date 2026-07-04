import com.google.firebase.appdistribution.gradle.AppDistributionExtension
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import com.toolkit.plugs.AppEnvironmentParameters
import com.toolkit.plugs.setupSigningConfigs

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.gms)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.firebase.appdistribution)
}
val parameters = AppEnvironmentParameters.from(envFilePath = "D:\\MEGA\\GitHub\\environments\\app-my-exams\\param.env")

android {
    namespace = "com.vald3nir.myexams"
    defaultConfig {
        applicationId = namespace
        versionCode = 14
        versionName = "2026.5.1"
        buildConfigField("String", "APP_PRIVACY_POLICY_URL", parameters.appPrivacyPolicyURL)
        buildConfigField("String", "APP_TERMS_USE_URL", parameters.termsUseURL)
        buildConfigField("String", "WEB_GOOGLE_CLIENT_ID", parameters.webGoogleClientID)
        buildConfigField("int", "DB_VERSION", versionCode.toString())
        buildConfigField("String", "SUPABASE_URL", parameters.supabaseUrl)
        buildConfigField("String", "SUPABASE_KEY", parameters.supabaseKey)
    }
    setupSigningConfigs(parameters = parameters)
    buildTypes.configureEach {
        extensions.configure<CrashlyticsExtension> {
            mappingFileUploadEnabled = false
        }
        if (name == "release") {
            configure<AppDistributionExtension> {
                serviceCredentialsFile = parameters.firebaseServiceCredentialsFilePath
                groups = "grupo-de-teste"
            }
        }
    }
}

baselineProfile {
    automaticGenerationDuringBuild = false
    dexLayoutOptimization = true
}

dependencies {
    implementation(project(":toolkit:core"))
    implementation(project(":toolkit:libs:auth"))
    implementation(project(":toolkit:libs:camera"))

    // PDF Reader
    implementation("com.tom-roush:pdfbox-android:2.0.27.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("buildProdVersion") {
    dependsOn("appDistributionUploadProdRelease")
    doLast {
        println("✓ Build prod release .aab gerado e enviado para Firebase App Distribution com sucesso!")
    }
}