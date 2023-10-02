plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    kotlin("plugin.serialization").version("1.8.21").apply(false)
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {
    val agp_version by extra("8.0")
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("com.android.tools.build:gradle:$agp_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
