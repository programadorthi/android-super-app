// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("configurations")
    id("project")
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}