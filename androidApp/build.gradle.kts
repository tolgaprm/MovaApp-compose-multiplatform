plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.splash.screen)
                implementation(libs.activity.compose)
            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.version.get().toInt()
    namespace = "com.prmto.mova"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.prmto.mova"
        minSdk = libs.versions.minSdk.version.get().toInt()
        targetSdk = libs.versions.targetSdk.version.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}