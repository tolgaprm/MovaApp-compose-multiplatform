plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", "com.prmto.mova")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.contentNegotiotion)
                // Voyager
                implementation(libs.bundles.voyager)
                // Koin
                api(libs.koin.core)
                // Paging
                implementation(libs.bundles.paging.library)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.assertK)
                implementation(libs.turbine)
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.version.get().toInt()
    namespace = "com.mova.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.minSdk.version.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}