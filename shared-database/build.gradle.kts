apply(from = "../gradle/commons.gradle")

plugins {
    kotlin("jvm")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("SuperApp") {
        packageName = "dev.programadorthi.shared.database"
    }
}
