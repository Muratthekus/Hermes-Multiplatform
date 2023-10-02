plugins {
    kotlin("multiplatform").version("1.8.21")
    kotlin("plugin.serialization").version("1.8.21")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")

}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
        }
    }

    sourceSets {
        val nonLinuxMain by creating
        val commonMain by getting {
            dependencies {
                api("dev.icerock.moko:resources:0.23.0")
                implementation(platform("io.github.jan-tennert.supabase:bom:1.1.1"))
                implementation("io.github.jan-tennert.supabase:postgrest-kt")
                implementation("io.github.jan-tennert.supabase:realtime-kt")
                implementation("io.github.jan-tennert.supabase:gotrue-kt")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.2.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.9.0")
                implementation("androidx.security:security-crypto:1.0.0")
                implementation("io.ktor:ktor-client-okhttp:2.2.0")
            }
        }

    }
}

android {
    namespace = "me.thekusch.hermes"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

multiplatformResources {
    multiplatformResourcesPackage = "me.thekusch.hermes"
    multiplatformResourcesClassName = "SharedRes"
}