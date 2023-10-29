plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlinx.kover")
}

apply(from = "${project.rootDir}/coverage_configuration/kover_filter.gradle")


dependencies {
    testImplementation("junit:junit:4.13.2")
}