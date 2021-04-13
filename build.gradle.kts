// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("configurations")
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
buildscript {
    val kotlin_version by extra("1.4.32")
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}
