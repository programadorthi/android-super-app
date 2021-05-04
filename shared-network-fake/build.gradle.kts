apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedNetwork)
    implementation(libs.kotlinx.coroutines.core)
}
