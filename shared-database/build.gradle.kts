apply(from = "../gradle/jvm-library.gradle")

plugins {
    kotlin("jvm")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("SuperApp") {
        packageName = "dev.programadorthi.shared.database"
    }
}
