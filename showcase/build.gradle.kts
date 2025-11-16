import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary) // Use library plugin instead of application for reusable showcase
}

kotlin {
    applyDefaultHierarchyTemplate()
    // Android target (correct invocation style)
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    // JVM (Desktop)
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    // JS (Browser)
    js(IR) { browser() }
    // WASM (Browser) - keep optional
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs { browser() }
    // iOS targets
    iosArm64()
    iosSimulatorArm64()

    jvmToolchain(11)

    explicitApi() // Library: surface clear API visibility

    sourceSets {
        val commonMain by getting {
            dependencies {
                // ...existing code...
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
            }
        }
        val commonTest by getting {
            dependencies { implementation(libs.kotlin.test) }
        }
        val androidMain by getting {
            dependencies { implementation(libs.androidx.activity.compose) }
        }
        val jvmMain by getting {
            dependencies { implementation(compose.desktop.currentOs) }
        }
        // Empty iOS / JS / WASM sourceSets kept for future extension
        val iosMain by getting
        val jsMain by getting
        val wasmJsMain by getting
    }
}

android {
    // ...existing code...
    namespace = "com.hp.showcase"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        // Provide proguard file only if later needed; keep placeholder
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    // buildFeatures { compose = true }  // Not required: handled by Compose Multiplatform plugin
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// group = "com.hp.innovrex.showcase"
// version = "0.1.0-SNAPSHOT"

// TODO: Verify composeMultiplatform (1.9.0) compatibility with kotlin 2.2.20; adjust if sync errors persist.
