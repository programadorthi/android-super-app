plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedDomain)
    implementation(libs.kodein.di)
    implementation(libs.kotlinx.coroutines.core)
}
