apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedNetwork)
    api(projects.sharedRetrofit)
    implementation(libs.kodein.di)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
}
