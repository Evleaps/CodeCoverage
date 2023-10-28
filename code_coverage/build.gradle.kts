plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.9.10"
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
    implementation("com.android.tools.build:gradle:8.1.2")
    implementation(kotlin("gradle-plugin", "1.9.10"))

    implementation(kotlin("serialization", "1.9.10"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
}
