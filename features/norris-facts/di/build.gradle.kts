apply(from = "../../../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.features.norrisFacts.domainImpl)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kodein.di)
    implementation(libs.retrofit)
}
