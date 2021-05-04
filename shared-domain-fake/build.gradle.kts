apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
}

dependencies {
    api(projects.sharedDomain)
}
