plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.8.22"
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("code-coverage-plugin") {
            id = "code-coverage-plugin"
            implementationClass = "CodeCoveragePlugin"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation(kotlin("gradle-plugin", "1.8.22"))

    implementation(kotlin("serialization", "1.8.22"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
}
