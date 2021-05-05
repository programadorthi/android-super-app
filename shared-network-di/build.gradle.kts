plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedNetwork)
    implementation(libs.kodein.di)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization)
}
