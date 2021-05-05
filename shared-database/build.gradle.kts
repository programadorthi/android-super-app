plugins {
    id("jvm-project")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("SuperApp") {
        packageName = "dev.programadorthi.shared.database"
    }
}
