import org.jetbrains.kotlin.ir.backend.js.compile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

//buildscript {
//    repositories {
//        maven {
//            url = uri("https://jitpack.io")
//        }
//    }
//
//    dependencies {
//        classpath("com.github.Evleaps:CodeCoverage:1.0.0-RC")
//    }
//}