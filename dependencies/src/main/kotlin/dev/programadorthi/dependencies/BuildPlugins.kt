package dev.programadorthi.dependencies

object BuildPlugins {
    const val android = "com.android.tools.build:gradle:4.1.3"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val ktlint = "org.jmailen.gradle:kotlinter-gradle:3.4.0"
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:1.4.4"
    const val testLogger = "com.adarshr:gradle-test-logger-plugin:2.1.1"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.34.1-beta"
}
