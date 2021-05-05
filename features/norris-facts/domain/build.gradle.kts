plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedDomain)
    implementation(libs.kotlinx.coroutines.core)
}
