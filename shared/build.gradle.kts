import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.prmto.mova"

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING, "API_KEY_TMDB",
            gradleLocalProperties(rootDir).getProperty("API_KEY_TMDB"),
            const = true
        )

        buildConfigField(
            FieldSpec.Type.STRING, "TMDB_BASE_URL",
            rootProject.requireStringProperty("TMDB_BASE_URL"),
            const = true
        )


        buildConfigField(
            FieldSpec.Type.STRING, "IMAGE_BASE_URL",
            rootProject.requireStringProperty("IMAGE_BASE_URL"),
            const = true
        )
    }
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
                // DateTime
                implementation(libs.kotlin.datetime)
                //Logger
                implementation(libs.kermit.logger)
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
                // Load Image
                implementation(libs.kamel.media)
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
                implementation(libs.android.lifecycle.runtime.compose)
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

fun Project.requireStringProperty(key: String): String {
    return (properties[key] as? String)!!
}