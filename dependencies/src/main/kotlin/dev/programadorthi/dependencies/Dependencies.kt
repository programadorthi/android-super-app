package dev.programadorthi.dependencies

object BuildPlugins {
    const val android = "com.android.tools.build:gradle:4.1.3"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val ktlint = "org.jmailen.gradle:kotlinter-gradle:3.4.0"
    const val testLogger = "com.adarshr:gradle-test-logger-plugin:2.1.1"
}

object Dependencies {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
    }

    object Android {
    }

    object Network {
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object UnitTest {
        val all = listOf(
            "junit:junit:4.13.1",
            "org.assertj:assertj-core:3.19.0",
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
        )
    }
}

object Version {
    const val KOTLIN = "1.4.31"
    const val COROUTINES = "1.4.3"
}
