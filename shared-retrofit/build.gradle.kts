apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.serializationConverter)
}
