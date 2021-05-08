plugins {
    id("jvm-project")
}

dependencies {
    api(projects.sharedNetwork)
    implementation(libs.kotlinx.coroutines.core)
}
