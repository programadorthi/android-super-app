apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.serializationConverter)
}
