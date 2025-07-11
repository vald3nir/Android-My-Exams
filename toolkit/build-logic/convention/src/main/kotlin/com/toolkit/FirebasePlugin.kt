import com.toolkit.plugs.appCompat
import com.toolkit.plugs.credentialsVersion
import com.toolkit.plugs.firebaseBom
import com.toolkit.plugs.googlePlayServiceGMSVersion
import com.toolkit.plugs.googleidCredentialsVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FirebasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Firebase Libs
                "implementation"(platform("com.google.firebase:firebase-bom:$firebaseBom"))
                "implementation"("com.google.firebase:firebase-storage-ktx")
                "implementation"("com.google.firebase:firebase-database-ktx")
                "implementation"("com.google.firebase:firebase-auth-ktx")
                "implementation"("com.google.firebase:firebase-crashlytics-ktx")

                // Firebase Auth Credentials
                "implementation"("androidx.credentials:credentials:$credentialsVersion")
                "implementation"("androidx.credentials:credentials-play-services-auth:$credentialsVersion")
                "implementation"("com.google.android.libraries.identity.googleid:googleid:${googleidCredentialsVersion}")

                // Google Login
                "implementation"("com.google.android.gms:play-services-auth:$googlePlayServiceGMSVersion")

                // Android Libraries
                "implementation"("androidx.appcompat:appcompat:$appCompat")
            }
        }
    }
}