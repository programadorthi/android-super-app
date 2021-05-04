apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedNetwork)
    implementation(libs.kodein.di)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
}
