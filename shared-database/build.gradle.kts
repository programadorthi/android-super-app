plugins {
    kotlin("jvm")
    id("com.squareup.sqldelight")
    id("super-module")
}

sqldelight {
    database("SuperApp") {
        packageName = "dev.programadorthi.shared.database"
    }
}
