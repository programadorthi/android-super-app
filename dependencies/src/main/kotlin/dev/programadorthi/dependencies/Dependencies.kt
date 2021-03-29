package dev.programadorthi.dependencies

object Dependencies {
    object Kotlin {
        val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
        val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"
    }

    object Android {
        const val plugin = "com.android.tools.build:gradle:4.1.3"
    }
}

object Version {
    const val KOTLIN = "1.4.31"
}