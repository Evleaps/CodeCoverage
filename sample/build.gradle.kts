// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
}

buildscript {
    repositories {
        // ...
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        classpath("com.github.Evleaps:CodeCoverage:v1.0.5-RC")
    }
}

apply(plugin = "code-coverage-plugin")
apply(from = "${project.rootDir}/coverage_configuration/code_coverage_configuration.gradle")
