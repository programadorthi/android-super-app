// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.savvasdalkitsis.module-dependency-graph") version "0.9"
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("org.jmailen.gradle:kotlinter-gradle:3.4.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.4")
        classpath("com.adarshr:gradle-test-logger-plugin:2.1.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
