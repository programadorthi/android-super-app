plugins {
    id("jvm-project")
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.serializationConverter)
}
