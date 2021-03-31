import dev.programadorthi.dependencies.Dependencies

plugins {
    kotlin("jvm")
    id("com.squareup.sqldelight")
    id("super-module")
}

sqldelight {
    database("Norris") {
        packageName = "dev.programadorthi.norris"
    }
}

dependencies {
    implementation(project(":shared-domain"))
    implementation(project(":applications:norris-facts:domain"))

    implementation(Dependencies.Kotlin.coroutines)
}
